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

    object AlreadyHoliday : CloseDaySideEffect()

    object TooManyHoliday : CloseDaySideEffect()

    object TooManyAnnualDay : CloseDaySideEffect()

    object AlreadyAnnualDay : CloseDaySideEffect()

    object AlreadyWork : CloseDaySideEffect()

    object CannotChangeWorkState : CloseDaySideEffect()
}
