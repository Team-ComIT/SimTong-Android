package com.comit.domain.repository

import com.comit.model.ScheduleList
import java.util.Date
import java.util.UUID

interface ScheduleRepository {

    suspend fun fetchPersonalSchedule(
        startAt: String,
        endAt: String
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
