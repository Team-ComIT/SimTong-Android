package com.comit.feature_mypage.mvi

data class FixEmailState(
    val email: String = "",
    val code: String = "",

    val errEmail: String ? = null,
    val errCode: String ? = null
)

sealed class FixEmailSideEffect {

    object EmailTextErrorException : FixEmailSideEffect()

    object SendCodeFinish : FixEmailSideEffect()

    object TooManyRequestsException : FixEmailSideEffect()

    object SameEmailException : FixEmailSideEffect()

    object ServerException : FixEmailSideEffect()

    object NoInternetException : FixEmailSideEffect()

    object CheckCodeSuccess : FixEmailSideEffect()

    object CheckCodeFail : FixEmailSideEffect()
}
