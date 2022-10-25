package com.comit.feature_auth.mvi.signup

import com.comit.feature_auth.screen.signup.SignUpStep

data class SignUpState(
    val currentPage: Int = 0,
)