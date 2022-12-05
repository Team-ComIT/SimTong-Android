@file:Suppress(
    "UnusedPrivateMember",
)

package com.comit.domain.exception

/**
 * 요청이 올바르지 않을 경우 발생하는 RuntimeException
 * Http Status가 400일 경우 발생합니다.
 *
 * @property fieldErrors list of field errors for request
 */
class BadRequestException(
    override val message: String?,
    private val fieldErrors: List<Pair<String, String>>,
) : RuntimeException()

/**
 * 인증이 실패 할 경우 발생하는 RuntimeException
 * Http Status가 401일 경우 발생합니다.
 */
class UnAuthorizedException(
    override val message: String?,
) : RuntimeException()

/**
 * 권한이 없을 경우 발생하는 RuntimeException
 * Http Status가 403일 경우 발생합니다.
 */
class ForBiddenException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청받은 리소스를 찾을 수 없을 때 발생하는 RuntimeException
 * Http Status가 404일 경우 발생합니다.
 */
class NotFoundException(
    override val message: String?,
) : RuntimeException()

/**
 * 요청이 지연될 경우 발생하는 RuntimeException
 * Http Status가 408일 경우 발생합니다.
 */
class TimeOutException(
    override val message: String?,
) : RuntimeException()

/**
 * 권한이 없을 경우 발생하는 RuntimeException
 * Http Status가 409일 경우 발생합니다.
 */
class ConflictException(
    override val message: String?,
) : RuntimeException()

/**
 * 서버에서 에러가 발생 할 경우 발생하는 RuntimeException
 * Http Status가 50X일 경우 발생합니다
 */
class ServerException(
    override val message: String?,
) : RuntimeException()

/**
 * 인터넷이 없을 경우 발생하는 RuntimeException
 */
class NoInternetException : RuntimeException()

/**
 * 예상치 못한 HttpException 발생할 경우의 RuntimeException
 */
class OtherHttpException(
    val code: Int,
    override val message: String?,
) : RuntimeException()

class UnknownException(
    override val message: String?,
) : RuntimeException()
