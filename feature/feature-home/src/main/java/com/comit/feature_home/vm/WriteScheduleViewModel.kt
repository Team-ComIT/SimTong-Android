package com.comit.feature_home.vm

import androidx.lifecycle.ViewModel
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.throwUnknownException
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
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class WriteScheduleViewModel @Inject constructor(
    private val addPersonalScheduleUseCase: AddPersonalScheduleUseCase,
    private val changePersonalScheduleUseCase: ChangePersonalScheduleUseCase,
) : ContainerHost<WriteScheduleState, WriteScheduleSideInEffect>, ViewModel() {

    override val container = container<WriteScheduleState, WriteScheduleSideInEffect>(WriteScheduleState())

    fun writeSchedule(
        title: String,
        scheduleStart: String,
        scheduleEnd: String,
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
            when (it) {
                is BadRequestException -> postSideEffect(WriteScheduleSideInEffect.InputTextFormError)
                else -> throwUnknownException(it)
            }
        }
    }

    fun changeSchedule(
        scheduleId: UUID,
        title: String,
        startAt: String,
        endAt: String,
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
            when (it) {
                is BadRequestException -> postSideEffect(WriteScheduleSideInEffect.InputTextFormError)
                is NotFoundException -> postSideEffect(WriteScheduleSideInEffect.CannotFindSchedule)
                else -> throwUnknownException(it)
            }
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

    fun inputErrMsgAll(msg: String) = intent {
        reduce { state.copy(errMsgTitle = "") }
        reduce { state.copy(errMsgScheduleStart = "") }
        reduce { state.copy(errMsgScheduleEnd = "") }
        reduce { state.copy(errMsgAlarm = msg) }
    }

    fun cancelErrMsgAll() = intent {
        reduce { state.copy(errMsgTitle = null) }
        reduce { state.copy(errMsgScheduleStart = null) }
        reduce { state.copy(errMsgScheduleEnd = null) }
        reduce { state.copy(errMsgAlarm = null) }
    }
}
