package com.comit.domain.usecase.holiday

import com.comit.domain.repository.HolidayRepository
import javax.inject.Inject

class FetchHolidaysUseCase @Inject constructor(
    private val repository: HolidayRepository,
) {

    suspend operator fun invoke(
        startAt: String,
        endAt: String,
        status: String,
    ) = kotlin.runCatching {
        repository.fetchHolidays(
            startAt = startAt,
            endAt = endAt,
            status = status,
        )
    }
}
