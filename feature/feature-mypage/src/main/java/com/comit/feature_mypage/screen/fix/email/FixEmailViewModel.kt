package com.comit.feature_mypage.screen.fix.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.ServerException
import com.comit.domain.exception.TooManyRequestsException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.usecase.email.CheckEmailCodeUseCase
import com.comit.domain.usecase.email.SendEmailCodeUseCase
import com.comit.domain.usecase.users.ChangeEmailUseCase
import com.comit.feature_mypage.mvi.FixEmailSideEffect
import com.comit.feature_mypage.mvi.FixEmailState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FixEmailViewModel @Inject constructor(
    private val checkEmailCodeUseCase: CheckEmailCodeUseCase,
    private val sendEmailCodeUseCase: SendEmailCodeUseCase,
) : ContainerHost<FixEmailState, FixEmailSideEffect>, ViewModel() {

    override val container = container<FixEmailState, FixEmailSideEffect>(FixEmailState())

    fun sendEmailCode(
        email: String,
    ) = intent {
        viewModelScope.launch {
            sendEmailCodeUseCase(
                email = email,
            ).onSuccess {
                postSideEffect(FixEmailSideEffect.SendCodeFinish)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FixEmailSideEffect.EmailTextErrorException)
                    is ConflictException -> postSideEffect(FixEmailSideEffect.SameEmailException)
                    is TooManyRequestsException -> postSideEffect(FixEmailSideEffect.TooManyRequestsException)
                    is ServerException -> postSideEffect(FixEmailSideEffect.ServerException)
                    is NoInternetException -> postSideEffect(FixEmailSideEffect.NoInternetException)
                }
            }
        }
    }

    fun checkEmailCode(
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
                postSideEffect(FixEmailSideEffect.CheckCodeSuccess)
            }.onFailure {
                postSideEffect(FixEmailSideEffect.CheckCodeFail)
            }
        }
    }

    fun inputMsgEmail(msg: String) = intent {
        reduce { state.copy(email = msg) }
    }

    fun inputMsgCode(msg: String) = intent {
        reduce { state.copy(code = msg) }
    }

    fun inputErrMsgEmail(msg: String?) = intent {
        reduce { state.copy(errEmail = msg) }
    }

    fun inputErrMsgCode(msg: String?) = intent {
        reduce { state.copy(errCode = msg) }
    }
}
