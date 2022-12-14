package com.comit.domain.usecase.holiday

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.HolidayRepository
import java.util.Date
import javax.inject.Inject

class SetAnnualUseCase @Inject constructor(
    private val repository: HolidayRepository,
) {

    suspend operator fun invoke(
        date: Date,
    ) = kotlin.runCatching {
        repository.setAnnual(
            date = date,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
