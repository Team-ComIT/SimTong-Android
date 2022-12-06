package com.comit.feature_mypage.mvi

data class FixEmailState(
    val email: String = "",

    val errEmail: String ? = null
)

sealed class FixEmailSideEffect {

    object EmailTextErrorException : FixEmailSideEffect()

    object FixEmailFinish : FixEmailSideEffect()

    object EmailCertificationException : FixEmailSideEffect()

    object SameEmailException : FixEmailSideEffect()

    object ServerException : FixEmailSideEffect()

    object NoInternetException : FixEmailSideEffect()
}
