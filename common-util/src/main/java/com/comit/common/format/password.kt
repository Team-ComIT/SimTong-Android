package com.comit.common.format

fun isPasswordFormat(password: String): Boolean {
    return password.matches(RegexUtils.SECRET_PATTERN.toRegex())
}
