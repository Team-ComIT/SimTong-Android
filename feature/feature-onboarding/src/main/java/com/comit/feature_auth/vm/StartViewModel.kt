package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.throwUnknownException
import com.comit.domain.usecase.commons.TokenReissueUseCase
import com.comit.feature_auth.mvi.StartSideEffect
import com.comit.feature_auth.mvi.StartState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val tokenReissueUseCase: TokenReissueUseCase,
) : ContainerHost<StartState, StartSideEffect>, ViewModel() {

    override val container = container<StartState, StartSideEffect>(StartState.Initial)

    fun authLogin() = intent {
        viewModelScope.launch {
            tokenReissueUseCase()
                .onSuccess {
                    postSideEffect(StartSideEffect.NavigateToHome)
                }.onFailure {
                    throwUnknownException(it)
                }
        }
    }
}
