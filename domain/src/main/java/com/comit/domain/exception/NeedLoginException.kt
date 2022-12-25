package com.comit.domain.exception

/**
 * RefreshToken 이 만료되어 로그인이 필요할 경우에 발생하는 Exception
 */
class NeedLoginException : RuntimeException() {
    override val message: String
        get() = "토큰이 만료되어 로그인이 필요합니다"
}

/**
 * RefreshToken 이 존재하지 않아 발생하는 Exception
 */
class RefreshTokenNotFound : RuntimeException() {
    override val message: String
        get() = "토큰이 존재하지 않습니다 로그인이 필요합니다."
}
