package com.comit.data.repository

import com.comit.data.datasource.RemoteScheduleDataSource
import com.comit.domain.repository.ScheduleRepository
import com.comit.model.ScheduleList
import java.util.UUID
import javax.inject.Inject

class ScheduleRepositoryImpl @Inject constructor(
    private val remoteScheduleDataSource: RemoteScheduleDataSource,
) : ScheduleRepository {

    override suspend fun fetchPersonalSchedule(
        startAt: String,
        endAt: String,
    ): ScheduleList {
        return remoteScheduleDataSource.fetchPersonalSchedule(
            startAt = startAt,
            endAt = endAt,
        )
    }

    override suspend fun addPersonalSchedule(
        title: String,
        startAt: String,
        endAt: String,
        alarm: String?,
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
        startAt: String,
        endAt: String,
        alarm: String?,
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
