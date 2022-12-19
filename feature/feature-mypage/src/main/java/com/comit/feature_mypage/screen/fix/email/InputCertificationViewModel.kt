package com.comit.feature_mypage.screen.fix.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.throwUnknownException
import com.comit.domain.usecase.email.CheckEmailCodeUseCase
import com.comit.domain.usecase.users.ChangeEmailUseCase
import com.comit.feature_mypage.mvi.InputCertificationSideEffect
import com.comit.feature_mypage.mvi.InputCertificationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class InputCertificationViewModel @Inject constructor(
    private val checkEmailCodeUseCase: CheckEmailCodeUseCase,
    private val changeEmailUseCase: ChangeEmailUseCase,
) : ContainerHost<InputCertificationState, InputCertificationSideEffect>, ViewModel() {

    override val container = container<InputCertificationState, InputCertificationSideEffect>(InputCertificationState())

    fun checkCertification(
        email: String,
        code: String,
    ) = intent {
        viewModelScope.launch {
            checkEmailCodeUseCase(
                params = CheckEmailCodeUseCase.Params(
                    email = email,
                    code = code,
                )
            ).onSuccess {
                postSideEffect(InputCertificationSideEffect.CertificationCorrect)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(InputCertificationSideEffect.CertificationNotValid)
                    is UnAuthorizedException -> postSideEffect(InputCertificationSideEffect.CertificationNotCorrect)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun changeEmail(
        email: String
    ) = intent {
        viewModelScope.launch {
            changeEmailUseCase(
                params = ChangeEmailUseCase.Params(
                    email = email
                )
            ).onSuccess {
                postSideEffect(InputCertificationSideEffect.ChangeEmailSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(InputCertificationSideEffect.EmailFormError)
                    is UnAuthorizedException -> postSideEffect(InputCertificationSideEffect.CheckEmailFail)
                    is ConflictException -> postSideEffect(InputCertificationSideEffect.SameEmailException)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun inputMsgCode(msg: String) = intent {
        reduce { state.copy(code = msg) }
    }

    fun inputErrMsgCode(msg: String ?) = intent {
        reduce { state.copy(errCode = msg) }
    }
}
