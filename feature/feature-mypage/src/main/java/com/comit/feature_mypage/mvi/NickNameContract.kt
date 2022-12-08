package com.comit.feature_mypage.mvi

data class FixNickNameState(
    val nickname: String = "",

    val errNicknameMsg: String ? = null,
)

sealed class FixNickNameSideEffect {

    object FixNickNameSuccess : FixNickNameSideEffect()

    object NickNameTextException : FixNickNameSideEffect()

    object TokenException : FixNickNameSideEffect()

    object SameNickNameException : FixNickNameSideEffect()

    object ServerException : FixNickNameSideEffect()
}
