package com.comit.common.format

fun isNickNameFormat(nickname: String): Boolean {
    return nickname.matches("""(?=^\S)(?=^[\w\s가-힣.]{1,20}$).*(?=\S$).*""".toRegex())
}
