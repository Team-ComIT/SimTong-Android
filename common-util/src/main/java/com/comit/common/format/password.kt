package com.comit.common.format

fun isPasswordFormat(password: String): Boolean {
    return password.matches("^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$._+-]).{8,20}\$".toRegex())

}
