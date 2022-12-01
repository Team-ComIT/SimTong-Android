package com.comit.feature_auth.mvi

import android.graphics.Bitmap
import com.comit.feature_auth.screen.signUp.SignUpStep

private val DefaultSignUpNameStep = SignUpStep.InputUserInfo.NAME
private val DefaultSignUpPasswordStep = SignUpStep.InputPassword.Password

data class SignUpState(
    val currentPage: Int = 0,

    val signUpNameStep: SignUpStep.InputUserInfo = DefaultSignUpNameStep,
    val signUpPasswordStep: SignUpStep.InputPassword = DefaultSignUpPasswordStep,

    val name: String = "",
    val employeeNumber: String = "",
    val email: String = "",
    val nickname: String = "",
    val profileImg: Bitmap? = null,
    val verifyCode: String = "",
)


sealed class SignUpSideEffect

