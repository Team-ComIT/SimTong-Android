package com.comit.feature_auth.mvi

data class FindPasswordState(
    val pageState: Int = 0,

    val employeeNumber: String = "",
    val email: String = "",
    val emailCode: String = "",
    val newPassword: String = "",
    val newPasswordCheck: String = "",

    val restartBtnEnabled: Boolean = false,

    val fieldErrEmployeeNumber: String? = null,
    val fieldErrEmail: String? = null,
    val fieldErrEmailCode: String? = null,
    val fieldErrNewPassword: String? = null,
    val fieldErrNewPasswordCheck: String? = null,
)

sealed class FindPasswordSideEffect {

    object NavigateToSignIn : FindPasswordSideEffect()

    object UserIsAlready : FindPasswordSideEffect()

    object UserNotFound : FindPasswordSideEffect()

    object EmailValidError : FindPasswordSideEffect()

    object EmployeeNumNotNum : FindPasswordSideEffect()

    object EmailVerifyAlready : FindPasswordSideEffect()

    object TooManyRequest : FindPasswordSideEffect()

    object NavigateToFixPassword : FindPasswordSideEffect()

    object EmailCodeNotCorrect : FindPasswordSideEffect()
}
