package com.comit.data.datasource

import com.comit.model.HolidayList
import com.comit.model.LeftHoliday
import java.util.Date

interface RemoteHolidayDataSource {

    suspend fun fetchHolidays(
        startAt: String,
        endAt: String,
        status: String,
    ): HolidayList

    suspend fun dayOffHolidays(
        date: String
    )

    suspend fun setAnnual(
        date: Date,
    )

    suspend fun setWork(
        date: Date
    )

    suspend fun checkLeftHoliday(
        year: Int
    ): LeftHoliday

    suspend fun checkCanWriteHoliday()
}
