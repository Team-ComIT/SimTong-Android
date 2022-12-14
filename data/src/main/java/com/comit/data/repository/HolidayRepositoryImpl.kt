package com.comit.data.repository

import com.comit.data.datasource.RemoteHolidayDataSource
import com.comit.domain.repository.HolidayRepository
import com.comit.model.HolidayList
import java.util.Date
import javax.inject.Inject

class HolidayRepositoryImpl @Inject constructor(
    private val remoteHolidayDataSource: RemoteHolidayDataSource,
) : HolidayRepository {

    override suspend fun fetchHolidays(
        date: Date,
    ): HolidayList {
        return remoteHolidayDataSource.fetchHolidays(
            date = date,
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
}
