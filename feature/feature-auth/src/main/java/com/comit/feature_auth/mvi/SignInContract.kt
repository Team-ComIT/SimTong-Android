package com.comit.feature_auth.mvi

data class SignInState(
    val employeeNumber: String = "",
    val password: String = "",

    val errMsgEmployeeNumber: String ?= null,
    val errMsgPassword: String ?= null,
)

sealed class SignInSideEffect {

    object NavigateToHomeScreen: SignInSideEffect()
}