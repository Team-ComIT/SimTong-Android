package com.comit.common.format

fun isPasswordFormat(password: String): Boolean {
    return password.matches("""(?=(.*[a-zA-Z].*)+)(?=(.*\d.*)+)(?=^[\w+\-$]{8,20}$).*""".toRegex())
}
