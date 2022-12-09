package com.comit.feature_home.screen.schedule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ShowScheduleViewModel @Inject constructor(
  private val fetchPersonalScheduleUseCase: FetchPersonalScheduleUseCase
) : ContainerHost<FetchScheduleState, FetchScheduleSideEffect>, ViewModel() {

  override val container = container<FetchScheduleState, FetchScheduleSideEffect>(FetchScheduleState())

  fun showSchedule(
    date: Date
  ) = intent {
    viewModelScope.launch {
      fetchPersonalScheduleUseCase(
        date = date
      ).onSuccess {
        reduce {
          state.copy(
            scheduleList = it.toState().scheduleList
          )
        }
      }.onFailure {
        postSideEffect(FetchScheduleSideEffect.FetchScheduleFail)
      }
    }
  }
}