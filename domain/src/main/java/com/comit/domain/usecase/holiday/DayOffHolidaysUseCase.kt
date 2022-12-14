package com.comit.domain.usecase.holiday

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.HolidayRepository
import javax.inject.Inject

class DayOffHolidaysUseCase @Inject constructor(
    private val repository: HolidayRepository,
) {

    suspend operator fun invoke(
        date: String,
    ) = kotlin.runCatching {
        repository.dayOffHolidays(
            date = date,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
