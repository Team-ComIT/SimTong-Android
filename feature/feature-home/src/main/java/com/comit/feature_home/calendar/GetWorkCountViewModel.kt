package com.comit.feature_home.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.schedule.FetchPersonalScheduleUseCase
import com.comit.feature_home.mvi.FetchScheduleState
import com.comit.feature_home.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetWorkCountViewModel @Inject constructor(
    private val fetchPersonalScheduleUseCase: FetchPersonalScheduleUseCase
) : ViewModel() {

    private val _workCountList: MutableLiveData<List<FetchScheduleState.Schedule>> = MutableLiveData()
    val workCountList: LiveData<List<FetchScheduleState.Schedule>> = _workCountList

    fun getWorkCountList(
        startAt: String,
        endAt: String,
    ) {
        viewModelScope.launch {
            fetchPersonalScheduleUseCase(
                startAt = startAt,
                endAt = endAt,
            ).onSuccess {
                _workCountList.value = it.toState().scheduleList
            }.onFailure {
                _workCountList.value = listOf()
            }
        }
    }
}
