@file:Suppress(
    "TooManyFunctions",
)

package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.common.format.isEmailFormat
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.TooManyRequestException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.usecase.commons.FindAccountExistUseCase
import com.comit.domain.usecase.commons.InitializationPasswordUseCase
import com.comit.domain.usecase.email.CheckEmailCodeUseCase
import com.comit.domain.usecase.email.SendEmailCodeUseCase
import com.comit.feature_auth.mvi.FindPasswordSideEffect
import com.comit.feature_auth.mvi.FindPasswordState
import com.comit.feature_auth.mvi.SignUpSideEffect
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
    private val findAccountExistUseCase: FindAccountExistUseCase,
    private val sendEmailCodeUseCase: SendEmailCodeUseCase,
    private val checkEmailCodeUseCase: CheckEmailCodeUseCase,
    private val initializationPasswordUseCase: InitializationPasswordUseCase,
) : ContainerHost<FindPasswordState, FindPasswordSideEffect>, ViewModel() {

    override val container =
        container<FindPasswordState, FindPasswordSideEffect>(FindPasswordState())

    fun initializationPassword(
        email: String,
        employeeNumber: String,
        newPassword: String,
    ) = intent {
        viewModelScope.launch {
            initializationPasswordUseCase(
                params = InitializationPasswordUseCase.Params(
                    email = email,
                    employeeNumber = employeeNumber.toInt(),
                    newPassword = newPassword,
                )
            ).onSuccess {
                postSideEffect(FindPasswordSideEffect.NavigateToSignIn)
            }.onFailure {

            }
        }
    }

    fun sendEmailCode() = intent {
        val email = state.email

        if (!email.isEmailFormat()) {
            postSideEffect(FindPasswordSideEffect.EmailFormat)
            return@intent
        }

        viewModelScope.launch {
            sendEmailCodeUseCase(
                email = email,
            ).onFailure {
                when (it) {
                    is ConflictException -> postSideEffect(FindPasswordSideEffect.EmailVerifyAlready)
                    is TooManyRequestException -> postSideEffect(FindPasswordSideEffect.TooManyRequest)
                }
            }
        }
    }

    fun checkEmailCode(
        email: String,
        emailCode: String,
    ) = intent {
        viewModelScope.launch {
            checkEmailCodeUseCase(
                params = CheckEmailCodeUseCase.Params(
                    email = email,
                    code = emailCode,
                ),
            ).onSuccess {
                postSideEffect(FindPasswordSideEffect.NavigateToFixPassword)
            }.onFailure {
                when (it) {
                    is UnAuthorizedException -> postSideEffect(FindPasswordSideEffect.EmailCodeNotCorrect)
                }
            }
        }
    }

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
        println("INPUT EMAIL $email")
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

    fun inputFieldErrEmailCode(
        msg: String?,
    ) = intent {
        reduce { state.copy(fieldErrEmailCode = msg) }
    }

    fun changedRestartBtnEnabled(
        enabled: Boolean,
    ) = intent {
        reduce { state.copy(restartBtnEnabled = enabled) }
    }

    fun inputNewPassword(
        newPassword: String,
    ) = intent {
        reduce { state.copy(newPassword = newPassword) }
    }

    fun inputNewPasswordCheck(
        newPasswordCheck: String,
    ) = intent {
        reduce { state.copy(newPasswordCheck = newPasswordCheck) }
    }
}
