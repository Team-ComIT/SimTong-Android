package com.comit.feature_home.mvi

data class CloseDayState(
    val messageFail: String = "일정 변경을 실패하였습니다."
)

sealed class CloseDaySideEffect {

    object CloseDayChangeSuccess : CloseDaySideEffect()

    object CloseDayChangeFail : CloseDaySideEffect()
}
