package com.comit.feature_home.screen.schedule

import androidx.lifecycle.ViewModel
import com.comit.domain.usecase.schedule.AddPersonalScheduleUseCase
import com.comit.feature_home.mvi.WriteScheduleSideInEffect
import com.comit.feature_home.mvi.WriteScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.sql.Time
import javax.inject.Inject

@HiltViewModel
class WriteScheduleViewModel @Inject constructor(
    private val addPersonalScheduleUseCase: AddPersonalScheduleUseCase,
) : ContainerHost<WriteScheduleState, WriteScheduleSideInEffect>, ViewModel() {

    override val container = container<WriteScheduleState, WriteScheduleSideInEffect>(WriteScheduleState())

    fun writeSchedule(
        title: String,
        scheduleStart: String,
        scheduleEnd: String,
        alarm: Time,
    ) = intent {
        addPersonalScheduleUseCase(
            params = AddPersonalScheduleUseCase.Params(
                title = title,
                startAt = scheduleStart,
                endAt = scheduleEnd,
                alarms = alarm
            )
        ).onSuccess {
            postSideEffect(WriteScheduleSideInEffect.WriteScheduleSuccess)
        }.onFailure {

        }
    }

    fun inputTitle(msg: String) = intent {
        reduce { state.copy(title = msg) }
    }

    fun inputScheduleStart(msg: String) = intent {
        reduce { state.copy(scheduleStart = msg) }
    }

    fun inputScheduleEnd(msg: String) = intent {
        reduce { state.copy(scheduleEnd = msg) }
    }

    fun inputAlarm(msg: String) = intent {
        reduce { state.copy(alarm = msg) }
    }
}