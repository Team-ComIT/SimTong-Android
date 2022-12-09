package com.comit.feature_home.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.holiday.FetchHolidaysUseCase
import com.comit.feature_home.mvi.FetchHolidaySideEffect
import com.comit.feature_home.mvi.FetchHolidayState
import com.comit.feature_home.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class GetHolidayViewModel @Inject constructor(
    private val fetchHolidaysUseCase: FetchHolidaysUseCase
) : ContainerHost<FetchHolidayState, FetchHolidaySideEffect>, ViewModel() {

    override val container = container<FetchHolidayState, FetchHolidaySideEffect>(FetchHolidayState())

    fun getHolidayList(
        date: Date
    ) = intent {
        viewModelScope.launch {
            fetchHolidaysUseCase(
                date = date
            ).onSuccess {
                reduce {
                    state.copy(
                        holidayList = it.toState().holidayList
                    )
                }
            }.onFailure {
                Log.d("TAG", "getHolidayList: 실패")
            }
        }
    }
}