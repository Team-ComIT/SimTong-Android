@file:Suppress(
    "TooGenericExceptionCaught",
    "SwallowedException",
    "MagicNumber",
    "RethrowCaughtException",
    "ComplexMethod",
)

package com.comit.data.util

import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.ForBiddenException
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.exception.NoConnectivityException
import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.OtherHttpException
import com.comit.domain.exception.ServerException
import com.comit.domain.exception.TimeOutException
import com.comit.domain.exception.TooManyRequestException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.UnknownException
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

const val ExpiredTokenMessage = "만료된 토큰"

/**
 * API 를 안전하게 호출할 때 사용 오류 발생 시 Exception을 throws 합니다.
 *
 * @param callFunction this is the function that is returning the watned object.
 */
suspend inline fun <T> simTongApiCall(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        val message: String = getErrorMessage(e)

        throw when (e.code()) {
            400 -> BadRequestException(
                message = message,
                fieldErrors = getFieldErrorMessage(e),
            )
            401 -> {
                if (e.message == ExpiredTokenMessage) {
                    NeedLoginException()
                } else {
                    UnAuthorizedException(
                        message = message,
                    )
                }
            }
            403 -> ForBiddenException(
                message = message,
            )
            404 -> NotFoundException(
                message = message,
            )
            409 -> ConflictException(
                message = message,
            )
            429 -> TooManyRequestException(
                message = message,
            )
            500, 501, 502, 503 -> ServerException(
                message = message,
            )
            else -> OtherHttpException(
                code = e.code(),
                message = message,
            )
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(
            message = e.message,
        )
    } catch (e: NeedLoginException) {
        throw e
    } catch (e: NoInternetException) {
        throw NoInternetException()
    } catch (e: NoConnectivityException) {
        throw NoConnectivityException()
    } catch (e: Exception) {
        throw UnknownException(
            message = e.message,
        )
    }
}

private data class ErrorResponse(
    @field:SerializedName("status")
    val status: Int,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("field_errors")
    val fieldErrors: List<FieldError>
) {
    data class FieldError(
        @field:SerializedName("field")
        val field: String,

        @field:SerializedName("value")
        val value: String,

        @field:SerializedName("reason")
        val reason: String,
    )
}

private const val UnknownErrorMessage: String = "알 수 없는 오류가 발생했습니다"

fun getErrorMessage(exception: HttpException): String {
    val response = getError(exception)

    return "[${response?.status ?: -1}] ${response?.message ?: UnknownErrorMessage}"
}

fun getFieldErrorMessage(exception: HttpException): List<Pair<String, String>> {
    val response = getError(exception)

    return response?.fieldErrors?.map { error ->
        Pair(error.field, error.reason)
    }?.toList() ?: emptyList()
}

/**
 * 서버에서 보내주는 Error 를 Parsing 하는 역할을 담당합니다.
 *
 * @param exception parsing 할 HttpException
 *
 * @return 서버로부터 전달된 ErrorResponse
 */
private fun getError(exception: HttpException): ErrorResponse? {
    val errorString = exception.response()?.errorBody()?.string()

    return Gson().fromJson(
        errorString, ErrorResponse::class.java
    )
}

suspend inline fun <T> trySafeReissueToken(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: Throwable) {
        throw NeedLoginException()
    }
}
