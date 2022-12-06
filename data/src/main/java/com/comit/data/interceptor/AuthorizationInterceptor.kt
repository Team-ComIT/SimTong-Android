package com.comit.data.interceptor

import com.comit.data.datasource.LocalAuthDataSource
import com.comit.data.datasource.RemoteCommonsDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject

private data class CustomRequest(
    val path: String,
    val method: CustomRestMethod,
)

// TODO ("[중요] limsaehyun - Interceptor 토큰 자동 재발급 로직 - SIMT-60")
// TODO ("limsaehyun - 모든 Method를 포함하는 ALL 을 만들기 - SIMT-50")
internal enum class CustomRestMethod {
    GET,
    POST,
    PUT,
    PATCH,
    DELETE,
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
    CustomRequest("/commons/spot", CustomRestMethod.GET)
)

class AuthorizationInterceptor @Inject constructor(
//    private val remoteCommonsDataSource: RemoteCommonsDataSource,
    private val localAuthDataSource: LocalAuthDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url().encodedPath()
        val method = request.method().toCustomRestMethod()

        if (ignoreRequest.contains(CustomRequest(path, method))) {
            return chain.proceed(request)
        }

        val expiredAt = runBlocking {
            localAuthDataSource.fetchExpiredAt().first()
        }

        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (expiredAt.isBefore(currentTime)) {
            val refreshToken = runBlocking {
                localAuthDataSource.fetchRefreshToken().first()
            }

//            val response = runBlocking {
//                remoteCommonsDataSource.tokenReissue(
//                    refreshToken = refreshToken,
//                )
//            }
//
//            runBlocking {
//                localAuthDataSource.apply {
//                    saveAccessToken(response.accessToken)
//                    saveRefreshToken(response.refreshToken)
//                    saveExpiredAt(response.accessTokenExp)
//                }
//            }
        }

        val accessToken = runBlocking { localAuthDataSource.fetchAccessToken() }

        return chain.proceed(
            request.newBuilder().addHeader(
                AUTHORIZATION,
                "$BEARER_HEADER $accessToken",
            ).build()
        )
    }

    companion object {
        // TODO ("limsaehyun - 좀 더 잘 처리하는 방법 고민하기 - SIMT-49")
        const val BEARER_HEADER = "Bearer"
        const val AUTHORIZATION = "Authorization"
    }
}
