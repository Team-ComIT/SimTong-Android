package com.comit.feature_home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.throwUnknownException
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
            }.onFailure {
                when (it) {
                    is BadRequestException -> _holidayList.value = listOf()
                    is UnAuthorizedException -> _holidayList.value = listOf()
                    else -> throwUnknownException(it)
                }
            }
        }
    }
}
