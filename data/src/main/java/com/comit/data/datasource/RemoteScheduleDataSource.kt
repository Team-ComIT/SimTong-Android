package com.comit.data.datasource

import com.comit.model.ScheduleList
import java.sql.Time
import java.util.Date
import java.util.UUID

interface RemoteScheduleDataSource {

    suspend fun fetchPersonalSchedule(
        date: Date,
    ): ScheduleList

    suspend fun addPersonalSchedule(
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: Time?,
    )

    suspend fun changePersonalSchedule(
        scheduleId: UUID,
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: Time?,
    )

    suspend fun deletePersonalSchedule(
        scheduleId: UUID,
    )
}
