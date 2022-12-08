package com.comit.feature_mypage.screen.fix.nickname

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.exception.BadRequestException
import com.comit.domain.exception.ConflictException
import com.comit.domain.exception.ForBiddenException
import com.comit.domain.exception.ServerException
import com.comit.domain.exception.UnAuthorizedException
import com.comit.domain.usecase.users.ChangeNicknameUseCase
import com.comit.feature_mypage.mvi.FixNickNameSideEffect
import com.comit.feature_mypage.mvi.FixNickNameState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FixNickNameViewModel @Inject constructor(
    private val changeNicknameUseCase: ChangeNicknameUseCase
) : ContainerHost<FixNickNameState, FixNickNameSideEffect>, ViewModel() {

    override val container = container<FixNickNameState, FixNickNameSideEffect>(FixNickNameState())

    fun fixNickName(
        nickname: String
    ) = intent {
        viewModelScope.launch {
            changeNicknameUseCase(
                params = ChangeNicknameUseCase.Params(
                    nickname = nickname
                )
            ).onSuccess {
                postSideEffect(FixNickNameSideEffect.FixNickNameSuccess)
            }.onFailure {
                when (it) {
                    is BadRequestException -> postSideEffect(FixNickNameSideEffect.NickNameTextException)
                    is UnAuthorizedException -> postSideEffect(FixNickNameSideEffect.TokenException)
                    is ForBiddenException -> postSideEffect(FixNickNameSideEffect.TokenException)
                    is ConflictException -> postSideEffect(FixNickNameSideEffect.SameNickNameException)
                    is ServerException -> postSideEffect(FixNickNameSideEffect.ServerException)
                }
            }
        }
    }

    fun inPutNickName(msg: String) = intent {
        reduce { state.copy(nickname = msg) }
    }

    fun inPutErrMsgNickName(msg: String?) = intent {
        reduce { state.copy(errNicknameMsg = msg) }
    }
}
