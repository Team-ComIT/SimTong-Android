package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.usecase.users.SignInUseCase
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
        employeeNumber: String,
        password: String,
    ) = intent {
        viewModelScope.launch {
            clearErrorMessage()

            if(employeeNumber.toIntOrNull() == null) {
                postSideEffect(SignInSideEffect.IdWasNotNumber)
                return@launch
            }

            signInUseCase(
                params = SignInUseCase.Params(
                    employeeNumber = employeeNumber.toInt(),
                    password = password
                ),
            ).onSuccess {
                postSideEffect(SignInSideEffect.NavigateToHomeScreen)
            }.onFailure {
                when (it) {
                    is UnAuthorizedException -> postSideEffect(SignInSideEffect.IdOrPasswordNotCorrect)
                    is NotFoundException -> postSideEffect(SignInSideEffect.IdOrPasswordNotCorrect)
                }
            }
        }
    }

    private fun clearErrorMessage() {
        inputErrMsgPassword(msg = null)
        inputErrMsgEmployeeNumber(msg = null)
    }

    fun inputEmployeeNumber(employeeNumber: String) = intent {
        reduce { state.copy(employeeNumber = employeeNumber) }
    }

    fun inputPassword(msg: String) = intent {
        reduce { state.copy(password = msg) }
    }

    fun inputErrMsgEmployeeNumber(msg: String?) = intent {
        reduce { state.copy(errMsgEmployeeNumber = msg) }
    }

    fun inputErrMsgPassword(msg: String?) = intent {
        reduce { state.copy(errMsgPassword = msg) }
    }
}
