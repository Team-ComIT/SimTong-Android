@file:Suppress("TooManyFunctions")

package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.common.format.isEmailFormat
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.TooManyRequestException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.usecase.email.CheckEmailCodeUseCase
import com.comit.domain.usecase.email.SendEmailCodeUseCase
import com.comit.domain.usecase.users.SignUpUseCase
import com.comit.domain.usecase.users.VerificationEmployeeUseCase
import com.comit.feature_auth.mvi.SignUpSideEffect
import com.comit.feature_auth.mvi.SignUpState
import com.comit.feature_auth.screen.signUp.SignUpStep
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

private const val EmployeeNumIsNum = "사원번호는 숫자로 이루어져 있어야 합니다"

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val verificationEmployeeUseCase: VerificationEmployeeUseCase,
    private val sendEmailCodeUseCase: SendEmailCodeUseCase,
    private val checkEmailCodeUseCase: CheckEmailCodeUseCase,
    private val signUpUseCase: SignUpUseCase,
) : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {

    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun verificationEmployee(
        name: String,
        employeeNumber: String,
    ) = intent {

        if (employeeNumber.toIntOrNull() == null) {
            reduce { state.copy(fieldErrEmployeeNumber = EmployeeNumIsNum) }
            return@intent
        }

        viewModelScope.launch {
            verificationEmployeeUseCase(
                params = VerificationEmployeeUseCase.Params(
                    name = name,
                    employeeNumber = employeeNumber,
                )
            ).onSuccess {
                postSideEffect(SignUpSideEffect.ChangeStepToInputEmail)
            }.onFailure {
                when (it) {
                    is UnAuthorizedException -> {
                        postSideEffect(SignUpSideEffect.UserInfoMatchingFailed)
                    }
                }
            }
        }
    }

    fun sendEmailCode() = intent {
        val email = state.email

        if(!email.isEmailFormat()) {
            postSideEffect(SignUpSideEffect.EmailValid)
            return@intent
        }

        viewModelScope.launch {
            sendEmailCodeUseCase(
                email = email,
            ).onSuccess {
                postSideEffect(SignUpSideEffect.NavigateToSignUpVerify(email))
            }.onFailure {
                when (it) {
                    is ConflictException -> postSideEffect(SignUpSideEffect.EmailVerifyAlready)
                    is TooManyRequestException -> postSideEffect(SignUpSideEffect.TooManyRequest)
                }
            }
        }
    }

    fun checkVerifyCode(
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
                postSideEffect(SignUpSideEffect.NavigateToSignUpPassword)
            }.onFailure {
                when (it) {
                    is UnAuthorizedException -> postSideEffect(SignUpSideEffect.EmailCodeNotCorrect)
                }
            }
        }
    }

    // TODO ("limsaehyun - Conflict를 분리해서 처리해야 함")
    fun signUp(
        name: String,
        employeeNumber: String,
        email: String,
        password: String,
        nickname: String?,
        profileImage: File?,
    ) = intent {
        viewModelScope.launch {
            signUpUseCase(
                params = SignUpUseCase.Params(
                    name = name,
                    employeeNumber = employeeNumber.toInt(),
                    email = email,
                    password = password,
                    nickname = nickname,
                    profileImage = profileImage,
                )
            ).onSuccess {
                postSideEffect(SignUpSideEffect.NavigateToHome)
            }.onFailure {
                when (it) {
                    is ConflictException -> postSideEffect(SignUpSideEffect.SignUpConflict)
                }
                println("SIGNUP ERROR $it")
            }
        }
    }

    fun navigatePage(page: Int) = intent {
        reduce { state.copy(currentPage = page) }
    }

    fun navigateNameStep(step: SignUpStep.InputUserInfo) = intent {
        reduce { state.copy(signUpNameStep = step) }
    }

    fun navigatePasswordStep(step: SignUpStep.InputPassword) = intent {
        reduce { state.copy(signUpPasswordStep = step) }
    }

    fun changeEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun changeEmployeeNumber(employeeNumber: String) = intent {
        reduce { state.copy(employeeNumber = employeeNumber) }
    }

    fun changeName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    fun changeNickname(nickname: String) = intent {
        reduce { state.copy(nickname = nickname) }
    }

    fun changeProfileImg(image: File) = intent {
        reduce { state.copy(profileImg = image) }
    }

    fun changeVerifyCode(code: String) = intent {
        reduce { state.copy(verifyCode = code) }
    }

    fun changePassword(password: String) = intent {
        reduce { state.copy(password = password) }
    }

    fun changeCheckPassword(password: String) = intent {
        reduce { state.copy(checkPassword = password) }
    }

    fun inputFieldErrEmployeeNumber(message: String?) = intent {
        reduce { state.copy(fieldErrEmployeeNumber = message) }
    }

    fun inputFieldErrVerifyCode(message: String) = intent {
        reduce { state.copy(fieldErrVerifyCode = message) }
    }

    fun inputFieldErrEmail(message: String) = intent {
        reduce { state.copy(fieldErrEmail = message) }
    }
}
