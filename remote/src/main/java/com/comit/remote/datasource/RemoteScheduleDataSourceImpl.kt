package com.comit.remote.datasource

import com.comit.data.datasource.RemoteScheduleDataSource
import com.comit.data.util.simTongApiCall
import com.comit.model.ScheduleList
import com.comit.remote.api.ScheduleAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.schedules.AddPersonalScheduleRequest
import com.comit.remote.request.schedules.ChangePersonalScheduleRequest
import java.util.Date
import java.util.UUID
import javax.inject.Inject

class RemoteScheduleDataSourceImpl @Inject constructor(
    private val scheduleAPI: ScheduleAPI,
) : RemoteScheduleDataSource {

    override suspend fun fetchPersonalSchedule(
        startAt: String,
        endAt: String,
    ): ScheduleList = simTongApiCall {
        scheduleAPI.fetchPersonalSchedule(
            startAt = startAt,
            endAt = endAt
        ).toModel()
    }

    override suspend fun addPersonalSchedule(
        title: String,
        startAt: String,
        endAt: String,
        alarm: String ?,
    ) = simTongApiCall {
        scheduleAPI.addPersonalSchedule(
            request = AddPersonalScheduleRequest(
                title = title,
                startAt = startAt,
                endAt = endAt,
                alarm = alarm,
            )
        )
    }

    override suspend fun changePersonalSchedule(
        scheduleId: UUID,
        title: String,
        startAt: String,
        endAt: String,
        alarm: String?,
    ) = simTongApiCall {
        scheduleAPI.changePersonalSchedule(
            scheduleId = scheduleId,
            request = ChangePersonalScheduleRequest(
                title = title,
                startAt = startAt,
                endAt = endAt,
                alarm = alarm,
            )
        )
    }

    override suspend fun deletePersonalSchedule(
        scheduleId: UUID,
    ) = simTongApiCall {
        scheduleAPI.deletePersonalSchedule(
            scheduleId = scheduleId,
        )
    }
}
