package com.comit.feature_mypage.screen.fix.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.NotFoundException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.exception.throwUnknownException
import com.comit.domain.usecase.commons.ChangePasswordUseCase
import com.comit.domain.usecase.commons.CheckOldPasswordUseCase
import com.comit.feature_mypage.mvi.FixPasswordInSideEffect
import com.comit.feature_mypage.mvi.FixPasswordState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FixPasswordViewModel @Inject constructor(
    private val checkOldPasswordUseCase: CheckOldPasswordUseCase,
    private val changePasswordUseCase: ChangePasswordUseCase,
) : ContainerHost<FixPasswordState, FixPasswordInSideEffect>, ViewModel() {

    override val container = container<FixPasswordState, FixPasswordInSideEffect>(FixPasswordState())

    fun checkOldPassword(
        oldPassword: String
    ) = intent {
        viewModelScope.launch {
            checkOldPasswordUseCase(
                CheckOldPasswordUseCase.Params(
                    oldPassword = oldPassword
                )
            ).onSuccess {
                postSideEffect(FixPasswordInSideEffect.OldPasswordCorrect)
            }.onFailure {
                when (it) {
                    is UnAuthorizedException -> postSideEffect(FixPasswordInSideEffect.OldPasswordNotCorrect)
                    is NotFoundException -> postSideEffect(FixPasswordInSideEffect.NoInputPasswordException)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun fixPassword(
        password: String,
        newPassword: String
    ) = intent {
        viewModelScope.launch {
            changePasswordUseCase(
                ChangePasswordUseCase.Params(
                    password = password,
                    newPassword = newPassword
                )
            ).onSuccess {
                postSideEffect(FixPasswordInSideEffect.FixPasswordSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FixPasswordInSideEffect.PasswordFormException)
                    is UnAuthorizedException -> postSideEffect(FixPasswordInSideEffect.OldPasswordNotCorrect)
                    else -> throwUnknownException(it)
                }
            }
        }
    }

    fun inPutOldPassword(msg: String) = intent {
        reduce { state.copy(oldPassword = msg) }
    }

    fun inPutErrOldPassword(msg: String?) = intent {
        reduce { state.copy(errMsgOldPassword = msg) }
    }

    fun inPutPassword(msg: String) = intent {
        reduce { state.copy(password = msg) }
    }

    fun inPutErrPassword(msg: String?) = intent {
        reduce { state.copy(errMsgPassword = msg) }
    }
}
