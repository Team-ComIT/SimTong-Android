package com.comit.remote.datasource

import com.comit.data.datasource.RemoteHolidayDataSource
import com.comit.data.util.simTongApiCall
import com.comit.model.HolidayList
import com.comit.model.LeftHoliday
import com.comit.remote.api.HolidayAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.holidays.DayOffRequest
import com.comit.remote.request.holidays.SetAnnualRequest
import com.comit.remote.request.holidays.SetWorkRequest
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
                date = date,
            )
        )
    }

    override suspend fun setAnnual(
        date: Date,
    ) = simTongApiCall {
        holidayAPI.setAnnual(
            setAnnualRequest = SetAnnualRequest(
                date = date.toString(),
            )
        )
    }

    override suspend fun setWork(
        date: Date,
    ) = simTongApiCall {
        holidayAPI.setWork(
            setWorkRequest = SetWorkRequest(
                date = date.toString(),
            )
        )
    }

    override suspend fun checkLeftHoliday(
        year: Int
    ): LeftHoliday = simTongApiCall {
        holidayAPI.checkLeftHoliday(
            year = year,
        ).toModel()
    }

    override suspend fun checkCanWriteHoliday() = simTongApiCall {
        holidayAPI.checkCanWriteHoliday()
    }
}
