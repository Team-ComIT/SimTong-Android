package com.comit.feature_mypage.mvi

data class FixPasswordState(
    val password: String = "",
    val oldPassword: String = "",

    val errMsgPassword: String ? = null,
    val errMsgOldPassword: String ? = null,
)

sealed class FixPasswordInSideEffect {

    object OldPasswordCorrect: FixPasswordInSideEffect()

    object OldPasswordNotCorrect: FixPasswordInSideEffect()

    object FixPasswordSuccess: FixPasswordInSideEffect()

    object FixPasswordFail: FixPasswordInSideEffect()
}