package com.comit.feature_mypage.screen.fix

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.ServerException
import com.comit.domain.exception.UnAuthorizedException
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
    private val fixEmailUseCase: ChangeEmailUseCase,
) : ContainerHost<FixEmailState, FixEmailSideEffect>, ViewModel() {

    override val container = container<FixEmailState, FixEmailSideEffect>(FixEmailState())

    fun fixEmail(
        email: String,
    ) = intent {
        viewModelScope.launch {
            fixEmailUseCase(
                params = ChangeEmailUseCase.Params(
                    email = email
                ),
            ).onSuccess {
                postSideEffect(FixEmailSideEffect.FixEmailFinish)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FixEmailSideEffect.EmailTextErrorException)
                    is UnAuthorizedException -> postSideEffect(FixEmailSideEffect.EmailCertificationException)
                    is ConflictException -> postSideEffect(FixEmailSideEffect.SameEmailException)
                    is ServerException -> postSideEffect(FixEmailSideEffect.ServerException)
                    is NoInternetException -> postSideEffect(FixEmailSideEffect.NoInternetException)
                }
            }
        }
    }

    fun inputMsg(msg: String) = intent {
        reduce { state.copy(email = msg) }
    }

    fun inputErrMsgEmail(msg: String) = intent {
        reduce { state.copy(errEmail = msg) }
    }
}
