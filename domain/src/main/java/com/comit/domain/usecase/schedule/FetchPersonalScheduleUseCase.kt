package com.comit.domain.usecase.schedule

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.ScheduleRepository
import java.util.Date
import javax.inject.Inject

class FetchPersonalScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository,
) {

    suspend operator fun invoke(
        startAt: String,
        endAt: String,
    ) = kotlin.runCatching {
        repository.fetchPersonalSchedule(
            startAt = startAt,
            endAt = endAt,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
