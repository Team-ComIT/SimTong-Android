package com.comit.feature_home.mvi

import com.comit.model.HolidayList

data class FetchHolidayState(
    val holidayList: List<Holiday>
) {
    data class Holiday(
        val date: String,
        val type: String,
    )
}

fun HolidayList.toState() = FetchHolidayState(
    holidayList = holidays.map {
        it.toStateHoliday()
    }
)

fun HolidayList.Holiday.toStateHoliday() = FetchHolidayState.Holiday(
    date = date,
    type = title,
)

sealed class FetchHolidaySideEffect {

    object FetchHolidaySuccess : FetchHolidaySideEffect()
}
