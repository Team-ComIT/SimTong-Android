package com.comit.feature_mypage.screen.fix.workplace

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.commons.FetchSpotsUseCase
import com.comit.domain.usecase.users.ChangeSpotUseCase
import com.comit.feature_mypage.mvi.FixWorkPlaceSideEffect
import com.comit.feature_mypage.mvi.FixWorkPlaceState
import com.comit.feature_mypage.mvi.toState
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
class FixWorkPlaceViewModel @Inject constructor(
    private val changeSpotUseCase: ChangeSpotUseCase,
    private val fetchSpotsUseCase: FetchSpotsUseCase,
) : ContainerHost<FixWorkPlaceState, FixWorkPlaceSideEffect>, ViewModel() {

    override val container = container<FixWorkPlaceState, FixWorkPlaceSideEffect>(FixWorkPlaceState())

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
                    postSideEffect(FixWorkPlaceSideEffect.FetchWorkPlaceFail)
                }
        }
    }

    fun changeWorkPlace(
        spotId: UUID
    ) = intent {
        viewModelScope.launch {
            changeSpotUseCase(
                params = ChangeSpotUseCase.Params(
                    spotId = spotId
                )
            ).onSuccess {
                postSideEffect(FixWorkPlaceSideEffect.ChangeWorkPlaceSuccess)
            }.onFailure {
                postSideEffect(FixWorkPlaceSideEffect.ChangeWorkPlaceFail)
            }
        }
    }

    fun inPutSpotId(msg: UUID?) = intent {
        reduce { state.copy(spotId = msg) }
    }

    fun inPutErrMsg(msg: String) = intent{
        reduce { state.copy(errMsgSpotList = msg) }
    }
}
