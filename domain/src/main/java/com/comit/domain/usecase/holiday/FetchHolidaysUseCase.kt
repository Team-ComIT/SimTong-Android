package com.comit.domain.usecase.holiday

import com.comit.domain.repository.HolidayRepository
import java.util.Date
import javax.inject.Inject

class FetchHolidaysUseCase @Inject constructor(
    private val repository: HolidayRepository,
) {

    suspend operator fun invoke(
        date: Date,
    ) = kotlin.runCatching {
        repository.fetchHolidays(
            date = date,
        )
    }
}
