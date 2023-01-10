package com.comit.feature_home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.throwUnknownException
import com.comit.domain.usecase.schedule.DeletePersonalScheduleUseCase
import com.comit.domain.usecase.schedule.FetchPersonalScheduleUseCase
import com.comit.feature_home.mvi.FetchScheduleSideEffect
import com.comit.feature_home.mvi.FetchScheduleState
import com.comit.feature_home.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ShowScheduleViewModel @Inject constructor(
    private val fetchPersonalScheduleUseCase: FetchPersonalScheduleUseCase,
    private val deletePersonalScheduleUseCase: DeletePersonalScheduleUseCase,
) : ContainerHost<FetchScheduleState, FetchScheduleSideEffect>, ViewModel() {

    override val container = container<FetchScheduleState, FetchScheduleSideEffect>(FetchScheduleState())

    fun showSchedule(
        startAt: String,
        endAt: String,
    ) = intent {
        viewModelScope.launch {
            fetchPersonalScheduleUseCase(
                startAt = startAt,
                endAt = endAt,
            ).onSuccess {
                reduce {
                    state.copy(
                        scheduleList = it.toState().scheduleList
                    )
                }
            }.onFailure {
                throwUnknownException(it)
            }
        }
    }

    fun deleteSchedule(
        id: UUID
    ) = intent {
        viewModelScope.launch {
            deletePersonalScheduleUseCase(
                scheduleId = id
            ).onSuccess {
                postSideEffect(FetchScheduleSideEffect.DeleteScheduleSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FetchScheduleSideEffect.DeleteScheduleDateError)
                    is NotFoundException -> postSideEffect(FetchScheduleSideEffect.DeleteScheduleCannotFound)
                    else -> throwUnknownException(it)
                }
            }
        }
    }
}
