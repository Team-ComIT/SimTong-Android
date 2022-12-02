package com.comit.domain.usecase.schedule

import com.comit.domain.repository.ScheduleRepository
import java.util.Date
import javax.inject.Inject

class FetchPersonalScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository,
) {

    suspend operator fun invoke(
        date: Date,
    ) = kotlin.runCatching {
        repository.fetchPersonalSchedule(
            date = date,
        )
    }
}
