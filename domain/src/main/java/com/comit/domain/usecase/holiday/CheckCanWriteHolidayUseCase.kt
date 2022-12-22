package com.comit.domain.usecase.holiday

import com.comit.domain.repository.HolidayRepository
import javax.inject.Inject

class CheckCanWriteHolidayUseCase @Inject constructor(
    private val holidayRepository: HolidayRepository,
) {

    suspend operator fun invoke() = kotlin.runCatching {
        holidayRepository.checkCanWriteHoliday()
    }
}
