@file:Suppress(
    "TooGenericExceptionCaught",
    "SwallowedException",
    "MagicNumber",
    "RethrowCaughtException",
)

package com.comit.data.util

import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.ForBiddenException
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.OtherHttpException
import com.comit.domain.exception.ServerException
import com.comit.domain.exception.TimeOutException
import com.comit.domain.exception.TooManyRequestException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.UnknownException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * API 를 안전하게 호출할 때 사용 오류 발생 시 Exception을 throws 합니다.
 * @param callFunction this is the function that is returning the watned object.
 *
 * TODO(BadRequest에서 field_errors를 Parsing하여 처리해줘야 함)
 */
suspend inline fun <T> simTongApiCall(
    crossinline callFunction: suspend () -> T,
): T {
    return try {
        withContext(Dispatchers.IO) {
            callFunction.invoke()
        }
    } catch (e: HttpException) {
        throw when (e.code()) {
            400 -> BadRequestException(
                message = e.message,
                fieldErrors = emptyList(),
            )
            401 -> {
                if (e.message == "만료된 토큰") {
                    NeedLoginException()
                } else {
                    UnAuthorizedException(
                        message = e.message(),
                    )
                }
            }
            403 -> ForBiddenException(
                message = e.message(),
            )
            404 -> NotFoundException(
                message = e.message(),
            )
            409 -> ConflictException(
                message = e.message(),
            )
            429 -> TooManyRequestException(
                message = e.message(),
            )
            500, 501, 502, 503 -> ServerException(
                message = e.message(),
            )
            else -> OtherHttpException(
                code = e.code(),
                message = e.message(),
            )
        }
    } catch (e: UnknownHostException) {
        throw NoInternetException()
    } catch (e: SocketTimeoutException) {
        throw TimeOutException(
            message = e.message,
        )
    } catch (e: Exception) {
        throw UnknownException(
            message = e.message,
        )
    } catch (e: NeedLoginException) {
        throw e
    }
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
