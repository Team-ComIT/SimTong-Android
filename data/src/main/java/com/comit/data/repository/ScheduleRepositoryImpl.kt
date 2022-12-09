package com.comit.data.repository

import com.comit.data.datasource.RemoteScheduleDataSource
import com.comit.domain.repository.ScheduleRepository
import com.comit.model.ScheduleList
import java.sql.Time
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteScheduleDataSource: RemoteScheduleDataSource,
) : ScheduleRepository {

    override suspend fun fetchPersonalSchedule(
        date: Date,
    ): ScheduleList {
        return remoteScheduleDataSource.fetchPersonalSchedule(
            date = date,
        )
    }

    override suspend fun addPersonalSchedule(
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: Time?,
    ) {
        remoteScheduleDataSource.addPersonalSchedule(
            title = title,
            startAt = startAt,
            endAt = endAt,
            alarm = alarm,
        )
    }

    override suspend fun changePersonalSchedule(
        scheduleId: UUID,
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: Time?,
    ) {
        remoteScheduleDataSource.changePersonalSchedule(
            scheduleId = scheduleId,
            title = title,
            startAt = startAt,
            endAt = endAt,
            alarm = alarm,
        )
    }

    override suspend fun deletePersonalSchedule(
        scheduleId: UUID,
    ) {
        remoteScheduleDataSource.deletePersonalSchedule(
            scheduleId = scheduleId,
        )
    }
}
