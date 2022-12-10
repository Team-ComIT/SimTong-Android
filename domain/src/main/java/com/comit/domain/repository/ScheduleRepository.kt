package com.comit.domain.repository

import com.comit.model.ScheduleList
import java.util.Date
import java.util.UUID

interface ScheduleRepository {

    suspend fun fetchPersonalSchedule(
        date: Date,
    ): ScheduleList

    suspend fun addPersonalSchedule(
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: String?,
    )

    suspend fun changePersonalSchedule(
        scheduleId: UUID,
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: String?,
    )

    suspend fun deletePersonalSchedule(
        scheduleId: UUID,
    )
}
