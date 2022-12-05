package com.comit.data.datasource

import com.comit.model.HolidayList
import java.util.Date

interface RemoteHolidayDataSource {

    suspend fun fetchHolidays(
        date: Date,
    ): HolidayList

    suspend fun dayOffHolidays(
        date: Date
    )

    suspend fun setAnnual(
        date: Date,
    )

    suspend fun setWork(
        date: Date
    )
}