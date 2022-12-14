package com.comit.domain.usecase.schedule

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.ScheduleRepository
import java.util.UUID
import javax.inject.Inject

class DeletePersonalScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository,
) {

    suspend operator fun invoke(
        scheduleId: UUID,
    ) = kotlin.runCatching {
        repository.deletePersonalSchedule(
            scheduleId = scheduleId,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }
}
