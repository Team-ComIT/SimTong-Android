package com.comit.common.format

fun isPasswordFormat(password: String): Boolean {
    return password.matches(RegexUtils.SECRET_PATTERN.toRegex())
}

fun isNicknameFormat(nickname: String): Boolean =
    nickname.matches(RegexUtils.NICKNAME_PATTERN.toRegex())
