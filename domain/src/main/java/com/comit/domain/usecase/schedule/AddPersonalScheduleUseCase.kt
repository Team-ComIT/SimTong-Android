package com.comit.domain.usecase.schedule

import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.domain.repository.ScheduleRepository
import javax.inject.Inject

class AddPersonalScheduleUseCase @Inject constructor(
    private val repository: ScheduleRepository,
) {

    suspend operator fun invoke(
        params: Params,
    ) = kotlin.runCatching {
        repository.addPersonalSchedule(
            title = params.title,
            startAt = params.startAt,
            endAt = params.endAt,
            alarm = params.alarms,
        )
    }.onFailure {
        if (it is UnknownException) throw NoInternetException()
    }

    data class Params(
        val title: String,
        val startAt: String,
        val endAt: String,
        val alarms: String?,
    )
}
