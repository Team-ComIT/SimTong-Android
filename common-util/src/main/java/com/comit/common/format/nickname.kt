package com.comit.common.format

fun String.isNicknameFormat(): Boolean =
    this.matches(RegexUtils.NICKNAME_PATTERN.toRegex())
