@file:Suppress("UnusedPrivateMember")

package com.comit.data.interceptor

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.comit.common.unit.toLocalDateTime
import com.comit.data.datasource.LocalAuthDataSource
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.exception.NoConnectivityException
import com.comit.domain.exception.NoInternetException
import com.comit.model.Token
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Socket
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

private data class CustomRequest(
    val path: String,
    val method: CustomRestMethod,
)

internal enum class CustomRestMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
    ALL,
}

internal fun String.toCustomRestMethod() =
    when (this) {
        "GET" -> CustomRestMethod.GET
        "POST" -> CustomRestMethod.POST
        "PUT" -> CustomRestMethod.PUT
        "PATCH" -> CustomRestMethod.PATCH
        "DELETE" -> CustomRestMethod.DELETE
        else -> CustomRestMethod.GET
    }

/**
 * authorization interceptor ignore request list
 */
private val ignoreRequest = listOf(
    CustomRequest("/users/tokens", CustomRestMethod.POST),
    CustomRequest("/users/verification-employee", CustomRestMethod.GET),
    CustomRequest("/users", CustomRestMethod.POST),
    CustomRequest("/users/nickname/duplication", CustomRestMethod.GET),
    CustomRequest("/commons/spot", CustomRestMethod.GET),
    CustomRequest("/commons/account/existence", CustomRestMethod.GET),
    CustomRequest("/emails/code", CustomRestMethod.POST),
    CustomRequest("/emails", CustomRestMethod.GET),
    CustomRequest("/files", CustomRestMethod.POST),
    CustomRequest("/commons/password/initialization", CustomRestMethod.PUT),
)

/**
 * Authorization과 NetworkConnection이 혼합된 Interceptor
 *
 * [ignoreRequest] 에 있는 항목은 대상에서 제외됨
 *
 * 아래와 같은 기능을 함
 *
 * 1. 인터넷이 없을 경우에는 exception throw
 * @throws NoConnectivityException
 * @throws NoInternetException
 *
 * 2. 토큰이 만료되었을 경우 Token Refresh
 *
 * 3. Refresh 토큰이 만료되었을 경우 exception throw
 * @throws NeedLoginException
 *
 * TODO(limsaehyun): 인터셉터의 분리에 관해서 고민 해봐야 함
 * NetworkConnectionInterceptor 를 분리 할 경우 Interceptor 가 2번 호출되는 문제를 겪었음
 * 이에 대한 개선 방법을 찾아보고 분리할 수 있다면 분리하는편이 좋음
 */
class AuthorizationInterceptor @Inject constructor(
    private val localAuthDataSource: LocalAuthDataSource,
    private val context: Context,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnectionOn(context)) {
            throw NoConnectivityException()
        } else if (!isInternetAvailable()) {
            throw NoInternetException()
        }

        val request = chain.request()

        val path = request.url.encodedPath
        val method = request.method.toCustomRestMethod()

        if (method == CustomRestMethod.ALL && ignoreRequest.any { it.path == path }) {
            return chain.proceed(request)
        }
        if (ignoreRequest.contains(CustomRequest(path, method))) {
            return chain.proceed(request)
        }

        val expiredAt: LocalDateTime = runBlocking {
            localAuthDataSource.fetchExpiredAt().first()
        }

        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val client = OkHttpClient()
            val refreshToken = runBlocking {
                localAuthDataSource.fetchRefreshToken().first()
            }

            val tokenRefreshRequest = Request.Builder()
                .url(REISSUE_TOKEN_URL)
                .put("".toRequestBody("application/json".toMediaTypeOrNull()))
                .addHeader(REFRESH_TOKEN, refreshToken)
                .build()
            val response = client.newCall(tokenRefreshRequest).execute()

            if (response.isSuccessful) {
                val token = Gson().fromJson(
                    response.body!!.string(),
                    ReissueToken::class.java
                )
                runBlocking {
                    localAuthDataSource.saveToken(
                        token = Token(
                            accessToken = token.accessToken,
                            accessTokenExp = token.accessTokenExp.toLocalDateTime(),
                            refreshToken = token.refreshToken,
                        )
                    )
                }
            } else throw NeedLoginException()
        }

        val accessToken = runBlocking {
            localAuthDataSource.fetchAccessToken().first()
        }

        return chain.proceed(
            request.newBuilder().addHeader(
                AUTHORIZATION,
                "$BEARER_HEADER $accessToken"
            ).build()
        )
    }

    private fun isConnectionOn(
        context: Context,
    ): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val connection = connectivityManager.getNetworkCapabilities(network)
            return connection != null && (
                connection.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    connection.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
                )
        } else {
            val activeNetwork = connectivityManager.activeNetworkInfo
            if (activeNetwork != null) {
                return (
                    activeNetwork.type == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.type == ConnectivityManager.TYPE_MOBILE
                    )
            }
            return false
        }
    }

    private fun isInternetAvailable(): Boolean {
        return try {
            val timeoutMs = 1500
            val sock = Socket()
            val sockaddr = InetSocketAddress("8.8.8.8", 53)

            sock.connect(sockaddr, timeoutMs)
            sock.close()

            true
        } catch (e: IOException) {
            false
        }
    }

    data class ReissueToken(
        @SerializedName("access_token")
        val accessToken: String,
        @SerializedName("access_token_exp")
        val accessTokenExp: String,
        @SerializedName("refresh_token")
        val refreshToken: String,
    )

    companion object {
        const val BEARER_HEADER = "Bearer"
        const val AUTHORIZATION = "Authorization"
        const val REFRESH_TOKEN = "Refresh-Token"
        const val REISSUE_TOKEN_URL = "http://3.39.162.197:8888/commons/token/reissue"
    }
}
