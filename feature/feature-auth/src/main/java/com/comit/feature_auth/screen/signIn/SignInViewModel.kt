package com.comit.feature_auth.screen.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.SignInUseCase
import com.comit.feature_auth.mvi.SignInSideEffect
import com.comit.feature_auth.mvi.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ContainerHost<SignInState, SignInSideEffect>, ViewModel() {

    override val container = container<SignInState, SignInSideEffect>(SignInState())

    fun signIn(
        employeeNumber: Int,
        password: String,
    ) = intent {
        viewModelScope.launch {
            signInUseCase(
                params = SignInUseCase.Params(
                    employeeNumber = employeeNumber,
                    password = password
                ),
            ).onSuccess {
                postSideEffect(SignInSideEffect.NavigateToHomeScreen)
            }.onFailure {
                reduce {
                    state.copy(errMsgEmployeeNumber = ERROR_MESSAGE_NOT_CORRECT)
                }
            }
        }
    }

    fun inputEmployeeNumber(employeeNumber: String) = intent {
        reduce { state.copy(employeeNumber = employeeNumber) }
    }

    fun inputPassword(password: String) = intent {
        reduce { state.copy(password = password) }
    }

    companion object {
        const val ERROR_MESSAGE_NOT_CORRECT = "정보가 일치하지 않습니다."
    }
}
