package com.comit.feature_auth.mvi

import com.comit.feature_auth.screen.signUp.SignUpStep
import java.io.File

private val DefaultSignUpNameStep = SignUpStep.InputUserInfo.NAME
private val DefaultSignUpPasswordStep = SignUpStep.InputPassword.Password

data class SignUpState(
    val currentPage: Int = 0,

    val signUpNameStep: SignUpStep.InputUserInfo = DefaultSignUpNameStep,
    val signUpPasswordStep: SignUpStep.InputPassword = DefaultSignUpPasswordStep,

    val name: String = "",
    val employeeNumber: String = "",
    val fieldErrEmployeeNumber: String? = null,
    val email: String = "",
    val fieldErrEmail: String? = null,

    val verifyCode: String = "",
    val fieldErrVerifyCode: String? = null,

    val password: String = "",
    val checkPassword: String = "",

    val nickname: String = "",
    val profileImg: File? = null,
)

sealed class SignUpSideEffect {

    object NavigateToSignUpName : SignUpSideEffect()
    object UserInfoMatchingFailed : SignUpSideEffect()
    object ChangeStepToInputEmail : SignUpSideEffect()

    object EmailVerifyAlready : SignUpSideEffect()
    object TooManyRequest : SignUpSideEffect()
    data class NavigateToSignUpVerify(
        val email: String
    ) : SignUpSideEffect()
    object EmailCodeNotCorrect : SignUpSideEffect()
    object EmailValid: SignUpSideEffect()

    object NavigateToSignUpPassword : SignUpSideEffect()

    object NavigateToSignUpNickName : SignUpSideEffect()

    object NavigateToHome : SignUpSideEffect()
    object SignUpConflict : SignUpSideEffect()
}
