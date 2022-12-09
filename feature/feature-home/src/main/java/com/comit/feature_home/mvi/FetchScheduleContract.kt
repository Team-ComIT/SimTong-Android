package com.comit.feature_home.mvi

import com.comit.model.ScheduleList

data class FetchScheduleState(
    val scheduleList: List<Schedule> = listOf()
) {
    data class Schedule(
        val id: String = "",
        val start_At: String = "",
        val end_At: String = "",
        val title: String = ""
    )
}

fun ScheduleList.toState() = FetchScheduleState(
    scheduleList = schedules.map {
        it.toStateSchedule()
    }
)

fun ScheduleList.Schedule.toStateSchedule() = FetchScheduleState.Schedule(
    id = id,
    start_At = startAt,
    end_At = endAt,
    title = title
)

sealed class FetchScheduleSideEffect {

    object FetchScheduleFail : FetchScheduleSideEffect()
}