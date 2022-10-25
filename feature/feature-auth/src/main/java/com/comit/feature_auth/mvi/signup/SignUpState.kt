package com.comit.feature_auth.mvi.signup

import android.graphics.Bitmap
import com.comit.feature_auth.screen.signup.SignUpStep

private val DefaultSignUpNameStep = SignUpStep.InputUserInfo.NAME
private val DefaultSignUpPasswordStep = SignUpStep.InputPassword.Password

private val _agreements: List<String> =
    listOf(
        "약관1",
        "약관2",
        "약관3",
        "약관4"
    )

data class SignUpState(
    val currentPage: Int = 0,

    val signUpNameStep: SignUpStep.InputUserInfo = DefaultSignUpNameStep,
    val signUpPasswordStep: SignUpStep.InputPassword = DefaultSignUpPasswordStep,

    val name: String = "",
    val employeeNumber: String = "",
    val email: String = "",
    val nickname: String = "",
    val profileImg: Bitmap ?= null,
    val verifyCode: String = "",
)