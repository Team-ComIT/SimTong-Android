package com.comit.model

data class ScheduleList(
    val schedules: List<Schedule>,
) {
    data class Schedule(
        val id: String,
        val startAt: String,
        val endAt: String,
        val title: String,
    )
}
