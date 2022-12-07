package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.usecase.commons.ChangePasswordUseCase
import com.comit.domain.usecase.commons.FindAccountExistUseCase
import com.comit.feature_auth.mvi.FindPasswordSideEffect
import com.comit.feature_auth.mvi.FindPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindPasswordViewModel @Inject constructor(
    private val changePasswordUseCase: ChangePasswordUseCase,
    private val findAccountExistUseCase: FindAccountExistUseCase,
) : ContainerHost<FindPasswordState, FindPasswordSideEffect>, ViewModel() {

    override val container =
        container<FindPasswordState, FindPasswordSideEffect>(FindPasswordState())

    fun checkAccountExist(
        employeeNum: String,
        email: String,
    ) = intent {
        inputFieldErrEmployeeNum(null)

        viewModelScope.launch {
            if (employeeNum.toIntOrNull() == null) {
                postSideEffect(FindPasswordSideEffect.EmployeeNumNotNum)
                return@launch
            }

            findAccountExistUseCase(
                params = FindAccountExistUseCase.Params(
                    employeeNumber = employeeNum.toIntOrNull() ?: return@launch,
                    email = email,
                )
            ).onSuccess {
                postSideEffect(FindPasswordSideEffect.UserIsAlready)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FindPasswordSideEffect.EmailValidError)
                    is NotFoundException -> postSideEffect(FindPasswordSideEffect.UserNotFound)
                }
            }
        }
    }

    fun changePassword(
        password: String,
        newPassword: String,
    ) {
        viewModelScope.launch {
            changePasswordUseCase(
                params = ChangePasswordUseCase.Params(
                    password = password,
                    newPassword = newPassword
                )
            ).onSuccess {
            }.onFailure {
            }
        }
    }

    fun navigatePage(
        screen: Int
    ) = intent {
        reduce { state.copy(pageState = screen) }
    }

    fun inputEmployeeNum(
        employeeNum: String,
    ) = intent {
        reduce { state.copy(employeeNumber = employeeNum) }
    }

    fun inputEmail(
        email: String,
    ) = intent {
        reduce { state.copy(email = email) }
    }

    fun inputEmailCode(
        code: String,
    ) = intent {
        reduce { state.copy(emailCode = code) }
    }

    fun inputFieldErrEmployeeNum(
        msg: String?,
    ) = intent {
        reduce { state.copy(fieldErrEmployeeNumber = msg) }
    }

    fun inputFieldErrEmail(
        msg: String?,
    ) = intent {
        reduce { state.copy(fieldErrEmail = msg) }
    }
}
