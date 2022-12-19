package com.comit.feature_home.screen.closeday

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.throwUnknownException
import com.comit.domain.usecase.holiday.DayOffHolidaysUseCase
import com.comit.domain.usecase.holiday.SetAnnualUseCase
import com.comit.domain.usecase.holiday.SetWorkUseCase
import com.comit.feature_home.mvi.CloseDaySideEffect
import com.comit.feature_home.mvi.CloseDayState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CloseDayViewModel @Inject constructor(
    private val dayOffHolidaysUseCase: DayOffHolidaysUseCase,
    private val setAnnualUseCase: SetAnnualUseCase,
    private val setWorkUseCase: SetWorkUseCase,
) : ContainerHost<CloseDayState, CloseDaySideEffect>, ViewModel() {

    override val container = container<CloseDayState, CloseDaySideEffect>(CloseDayState())

    fun setHoliday(date: String) = intent {
        viewModelScope.launch {
            dayOffHolidaysUseCase(
                date = date
            ).onSuccess {
                postSideEffect(CloseDaySideEffect.CloseDayChangeSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(CloseDaySideEffect.DateInputWrong)
                    is UnAuthorizedException -> postSideEffect(CloseDaySideEffect.TokenException)
                    is ConflictException -> postSideEffect(CloseDaySideEffect.DayOffExcess)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun setAnnualDay(date: Date) = intent {
        viewModelScope.launch {
            setAnnualUseCase(
                date = date
            ).onSuccess {
                postSideEffect(CloseDaySideEffect.CloseDayChangeSuccess)
            }.onFailure {
                postSideEffect(CloseDaySideEffect.AnnualDayChangeFail)
            }
        }
    }

    fun setWorkDay(date: Date) = intent {
        viewModelScope.launch {
            setWorkUseCase(
                date = date
            ).onSuccess {
                postSideEffect(CloseDaySideEffect.CloseDayChangeSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(CloseDaySideEffect.DateInputWrong)
                    is UnAuthorizedException -> postSideEffect(CloseDaySideEffect.TokenException)
                    is NotFoundException -> postSideEffect(CloseDaySideEffect.AlreadyWork)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun inputYearState(msg: String) = intent {
        reduce { state.copy(year = msg) }
    }

    fun inputMonthState(msg: String) = intent {
        reduce { state.copy(month = msg) }
    }

    fun inputDayState(msg: String) = intent {
        reduce { state.copy(day = msg) }
    }
}
