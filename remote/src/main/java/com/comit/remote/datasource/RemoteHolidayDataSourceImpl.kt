package com.comit.remote.datasource

import com.comit.data.datasource.RemoteHolidayDataSource
import com.comit.data.util.simTongApiCall
import com.comit.model.HolidayList
import com.comit.remote.api.HolidayAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.holidays.DayOffRequest
import java.util.Date
import javax.inject.Inject

class RemoteHolidayDataSourceImpl @Inject constructor(
    private val holidayAPI: HolidayAPI,
) : RemoteHolidayDataSource {

    override suspend fun fetchHolidays(
        startAt: String,
        endAt: String,
        status: String
    ): HolidayList = simTongApiCall {
        holidayAPI.fetchHolidays(
            startAt = startAt,
            endAt = endAt,
            status = status,
        ).toModel()
    }

    override suspend fun dayOffHolidays(
        date: String,
    ) = simTongApiCall {
        holidayAPI.dayOffHolidays(
            dayOffRequest = DayOffRequest(
                date = date
            )
        )
    }

    override suspend fun setAnnual(
        date: Date,
    ) = simTongApiCall {
        holidayAPI.setAnnual(
            date = date,
        )
    }

    override suspend fun setWork(
        date: Date,
    ) = simTongApiCall {
        holidayAPI.setWork(
            date = date,
        )
    }
}
