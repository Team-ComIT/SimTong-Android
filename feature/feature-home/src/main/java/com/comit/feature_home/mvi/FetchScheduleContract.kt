package com.comit.feature_home.mvi

import com.comit.model.ScheduleList

data class FetchScheduleState(
    val scheduleList: List<Schedule> = listOf()
) {
    data class Schedule(
        val id: String = "",
        val startAt: String = "",
        val endAt: String = "",
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
    startAt = startAt,
    endAt = endAt,
    title = title
)

sealed class FetchScheduleSideEffect {

    object DeleteScheduleDateError : FetchScheduleSideEffect()

    object DeleteScheduleCannotFound : FetchScheduleSideEffect()

    object DeleteScheduleSuccess : FetchScheduleSideEffect()
}
