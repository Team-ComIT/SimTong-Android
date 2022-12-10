package com.comit.feature_mypage.mvi

data class MyPageState(
    val name: String = "",
    val email: String = "",
    val nickname: String = "",
    val spot: String = "",
    val profileImagePath: String? = null,
)

sealed class MyPageSideEffect
