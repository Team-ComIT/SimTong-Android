package com.comit.feature_home.screen.schedule

import androidx.lifecycle.ViewModel
import com.comit.domain.usecase.schedule.AddPersonalScheduleUseCase
import com.comit.domain.usecase.schedule.ChangePersonalScheduleUseCase
import com.comit.feature_home.mvi.WriteScheduleSideInEffect
import com.comit.feature_home.mvi.WriteScheduleState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.Date
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WriteScheduleViewModel @Inject constructor(
    private val addPersonalScheduleUseCase: AddPersonalScheduleUseCase,
    private val changePersonalScheduleUseCase: ChangePersonalScheduleUseCase,
) : ContainerHost<WriteScheduleState, WriteScheduleSideInEffect>, ViewModel() {

    override val container = container<WriteScheduleState, WriteScheduleSideInEffect>(WriteScheduleState())

    // TODO(limsaehyun): 예상치 못한 예외 시 throwUnknownException 반환 필요
    fun writeSchedule(
        title: String,
        scheduleStart: Date,
        scheduleEnd: Date,
        alarm: String?,
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
            postSideEffect(WriteScheduleSideInEffect.WriteScheduleFail)
        }
    }

    // TODO(limsaehyun): 예상치 못한 예외 시 throwUnknownException 반환 필요
    fun changeSchedule(
        scheduleId: UUID,
        title: String,
        startAt: Date,
        endAt: Date,
        alarm: String?,
    ) = intent {
        changePersonalScheduleUseCase(
            params = ChangePersonalScheduleUseCase.Params(
                scheduleId = scheduleId,
                title = title,
                startAt = startAt,
                endAt = endAt,
                alarms = alarm
            )
        ).onSuccess {
            postSideEffect(WriteScheduleSideInEffect.WriteScheduleSuccess)
        }.onFailure {
            postSideEffect(WriteScheduleSideInEffect.WriteScheduleFail)
        }
    }

    fun inputTitle(msg: String) = intent {
        reduce { state.copy(title = msg) }
    }

    fun inputErrMsgTitle(msg: String?) = intent {
        reduce { state.copy(errMsgTitle = msg) }
    }

    fun inputScheduleStart(msg: String) = intent {
        reduce { state.copy(scheduleStart = msg) }
    }

    fun inputErrMsgScheduleStart(msg: String?) = intent {
        reduce { state.copy(errMsgScheduleStart = msg) }
    }

    fun inputScheduleEnd(msg: String) = intent {
        reduce { state.copy(scheduleEnd = msg) }
    }

    fun inputErrMsgScheduleEnd(msg: String?) = intent {
        reduce { state.copy(errMsgScheduleEnd = msg) }
    }

    fun inputAlarm(msg: String) = intent {
        reduce { state.copy(alarm = msg) }
    }

    fun inputErrMsgAlarm(msg: String?) = intent {
        reduce { state.copy(errMsgAlarm = msg) }
    }
}
