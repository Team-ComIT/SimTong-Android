package com.comit.feature_mypage.mvi

data class InputCertificationState(
    val code: String = "",

    val errCode: String ? = null,
)

sealed class InputCertificationSideEffect {

    object CertificationCorrect : InputCertificationSideEffect()

    object CertificationNotValid : InputCertificationSideEffect()

    object CertificationNotCorrect : InputCertificationSideEffect()

    object ChangeEmailSuccess : InputCertificationSideEffect()

    object EmailFormError : InputCertificationSideEffect()

    object CheckEmailFail : InputCertificationSideEffect()

    object SameEmailException : InputCertificationSideEffect()
}
