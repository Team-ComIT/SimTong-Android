package com.comit.domain.usecase.holiday

import com.comit.domain.repository.HolidayRepository
import javax.inject.Inject

class CheckLeftHolidayUseCase @Inject constructor(
    private val repository: HolidayRepository,
) {

    suspend operator fun invoke(
        year: Int
    ) = kotlin.runCatching {
        repository.checkLeftHoliday(
            year = year,
        )
    }
}
