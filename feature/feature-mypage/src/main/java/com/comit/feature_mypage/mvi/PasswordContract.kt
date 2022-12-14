package com.comit.feature_mypage.mvi

data class FixPasswordState(
    val password: String = "",
    val oldPassword: String = "",

    val errMsgPassword: String ? = null,
    val errMsgOldPassword: String ? = null,
)

sealed class FixPasswordInSideEffect {

    object OldPasswordCorrect : FixPasswordInSideEffect()

    object OldPasswordNotCorrect : FixPasswordInSideEffect()

    object PasswordFormException : FixPasswordInSideEffect()

    object NoInputPasswordException : FixPasswordInSideEffect()

    object FixPasswordSuccess : FixPasswordInSideEffect()
}
