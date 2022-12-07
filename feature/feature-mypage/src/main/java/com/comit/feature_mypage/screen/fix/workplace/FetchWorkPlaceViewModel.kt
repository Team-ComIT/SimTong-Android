package com.comit.feature_mypage.screen.fix.workplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.commons.FetchSpotsUseCase
import com.comit.feature_mypage.mvi.FetchWorkPlaceSideEffect
import com.comit.feature_mypage.mvi.FetchWorkPlaceState
import com.comit.feature_mypage.mvi.toState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FetchWorkPlaceViewModel @Inject constructor(
    private val fetchSpotsUseCase: FetchSpotsUseCase,
) : ContainerHost<FetchWorkPlaceState, FetchWorkPlaceSideEffect>, ViewModel() {

    override val container = container<FetchWorkPlaceState, FetchWorkPlaceSideEffect>(FetchWorkPlaceState())

    fun fetchWorkPlace() = intent {
        viewModelScope.launch {
            fetchSpotsUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            spotList = it.toState().spotList
                        )
                    }
                }.onFailure {
                    postSideEffect(FetchWorkPlaceSideEffect.FetchWorkPlaceFail)
                }
        }
    }

    fun inPutErrMsg(msg: String) = intent{
        reduce { state.copy(errMsgSpotList = msg) }
    }
}