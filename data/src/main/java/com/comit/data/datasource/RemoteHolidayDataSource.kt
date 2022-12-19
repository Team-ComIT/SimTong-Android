package com.comit.data.datasource

import com.comit.model.HolidayList
import java.util.Date

interface RemoteHolidayDataSource {

    suspend fun fetchHolidays(
        startAt: String,
        endAt: String,
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
}
