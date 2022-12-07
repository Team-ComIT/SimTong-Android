package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.commons.FetchSpotsUseCase
import com.comit.domain.usecase.commons.FindEmployeeNumberUseCase
import com.comit.feature_auth.mvi.FindEmployeeNumSideEffect
import com.comit.feature_auth.mvi.FindEmployeeNumState
import com.comit.feature_auth.screen.find.employeeNumber.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindEmployeeNumViewModel @Inject constructor(
    private val findEmployeeNumberUseCase: FindEmployeeNumberUseCase,
    private val fetchSpotsUseCase: FetchSpotsUseCase,
) : ContainerHost<FindEmployeeNumState, FindEmployeeNumSideEffect>, ViewModel() {

    override val container =
        container<FindEmployeeNumState, FindEmployeeNumSideEffect>(FindEmployeeNumState())

    fun findEmployeeNum(
        name: String,
        spotId: String,
        email: String,
    ) = intent {
        viewModelScope.launch {
            findEmployeeNumberUseCase(
                params = FindEmployeeNumberUseCase.Params(
                    name = name,
                    spotId = spotId,
                    email = email,
                )
            ).onSuccess {
                postSideEffect(
                    sideEffect = FindEmployeeNumSideEffect.NavigateToResultScreen(it)
                )
            }.onFailure {
            }
        }
    }

    fun fetchSpot() = intent {
        viewModelScope.launch {
            fetchSpotsUseCase()
                .onSuccess {
                    reduce { state.copy(placeList = it.toUiModel()) }
                    postSideEffect(FindEmployeeNumSideEffect.FetchSpot)
                }.onFailure {
                }
        }
    }

    fun inputName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    fun inputEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun inputPlace(place: String) = intent {
        reduce { state.copy(place = place) }
    }

    fun inputPlaceId(placeId: String) = intent {
        reduce { state.copy(placeId = placeId) }
    }
}
