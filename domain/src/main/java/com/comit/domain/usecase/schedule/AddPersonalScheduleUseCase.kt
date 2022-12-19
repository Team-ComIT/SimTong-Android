package com.comit.domain.usecase.schedule

import com.comit.domain.repository.ScheduleRepository
import java.util.Date
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
    }

    data class Params(
        val title: String,
        val startAt: Date,
        val endAt: Date,
        val alarms: String?,
    )
}
