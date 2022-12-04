package com.comit.remote.datasource

import com.comit.data.datasource.RemoteHolidayDataSource
import com.comit.model.HolidayList
import com.comit.remote.api.HolidayAPI
import com.comit.remote.mapper.toModel
import java.util.Date
import javax.inject.Inject

class RemoteHolidayDataSourceImpl @Inject constructor(
    private val holidayAPI: HolidayAPI,
) : RemoteHolidayDataSource {

    override suspend fun fetchHolidays(
        date: Date,
    ): HolidayList {
        return holidayAPI.fetchHolidays(
            date = date,
        ).toModel()
    }

    override suspend fun dayOffHolidays(
        date: Date,
    ) {
        holidayAPI.dayOffHolidays(
            date = date,
        )
    }

    override suspend fun setAnnual(
        date: Date,
    ) {
        holidayAPI.setAnnual(
            date = date,
        )
    }

    override suspend fun setWork(
        date: Date,
    ) {
        holidayAPI.setWork(
            date = date,
        )
    }
}
