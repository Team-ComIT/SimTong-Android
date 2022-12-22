package com.comit.feature_home.mvi

data class CloseDayState(
    val year: String = "",
    val month: String = "",
    val day: String = "",
    val leftHoliday: Int = 0,
)

sealed class CloseDaySideEffect {

    object CloseDayChangeSuccess : CloseDaySideEffect()

    object DateInputWrong : CloseDaySideEffect()

    object TokenException : CloseDaySideEffect()

    object DayOffExcess : CloseDaySideEffect()

    object AnnualDayChangeFail : CloseDaySideEffect()

    object AlreadyWork : CloseDaySideEffect()
}
