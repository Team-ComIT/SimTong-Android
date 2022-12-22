package com.comit.domain.repository

import com.comit.model.HolidayList
import java.util.Date

interface HolidayRepository {

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
}
