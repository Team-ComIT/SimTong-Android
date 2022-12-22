package com.comit.feature_home.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.holiday.FetchHolidaysUseCase
import com.comit.feature_home.mvi.FetchHolidayState
import com.comit.feature_home.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetHolidayViewModel @Inject constructor(
    private val fetchHolidaysUseCase: FetchHolidaysUseCase
) : ViewModel() {

    private val _holidayList: MutableLiveData<List<FetchHolidayState.Holiday>> = MutableLiveData()
    val holidayList: LiveData<List<FetchHolidayState.Holiday>> = _holidayList

    fun getHolidayList(
        startAt: String,
        endAt: String,
        status: String,
    ) {
        viewModelScope.launch {
            fetchHolidaysUseCase(
                startAt = startAt,
                endAt = endAt,
                status = status,
            ).onSuccess {
                _holidayList.value = it.toState().holidayList
            }.onFailure {}
        }
    }
}
