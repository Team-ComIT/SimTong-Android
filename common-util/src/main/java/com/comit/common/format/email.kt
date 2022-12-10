package com.comit.common.format

fun String.isEmailFormat(): Boolean =
    android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()