package com.comit.data.repository

import com.comit.data.datasource.RemoteHolidayDataSource
import com.comit.domain.repository.HolidayRepository
import com.comit.model.HolidayList
import com.comit.model.LeftHoliday
import java.util.Date
import javax.inject.Inject

class HolidayRepositoryImpl @Inject constructor(
    private val remoteHolidayDataSource: RemoteHolidayDataSource,
) : HolidayRepository {

    override suspend fun fetchHolidays(
        startAt: String,
        endAt: String,
        status: String
    ): HolidayList {
        return remoteHolidayDataSource.fetchHolidays(
            startAt = startAt,
            endAt = endAt,
            status = status,
        )
    }

    override suspend fun dayOffHolidays(
        date: String,
    ) {
        remoteHolidayDataSource.dayOffHolidays(
            date = date,
        )
    }

    override suspend fun setAnnual(
        date: Date,
    ) {
        remoteHolidayDataSource.setAnnual(
            date = date,
        )
    }

    override suspend fun setWork(
        date: Date,
    ) {
        remoteHolidayDataSource.setWork(
            date = date,
        )
    }

    override suspend fun checkLeftHoliday(
        year: Int
    ): LeftHoliday {
        return remoteHolidayDataSource.checkLeftHoliday(
            year = year
        )
    }

    override suspend fun checkCanWriteHoliday() {
        remoteHolidayDataSource.checkCanWriteHoliday()
    }
}
