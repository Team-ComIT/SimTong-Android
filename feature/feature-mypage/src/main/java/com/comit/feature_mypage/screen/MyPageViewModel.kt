package com.comit.feature_mypage.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.users.FetchUserInformationUseCase
import com.comit.feature_mypage.mvi.MyPageSideEffect
import com.comit.feature_mypage.mvi.MyPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val fetchUserInformationUseCase: FetchUserInformationUseCase
) : ContainerHost<MyPageState, MyPageSideEffect>, ViewModel() {

    override val container = container<MyPageState, MyPageSideEffect>(MyPageState())

    fun fetchUserInformation() = intent {
        viewModelScope.launch {
            fetchUserInformationUseCase()
                .onSuccess {
                    reduce {
                        state.copy(
                            name = it.name,
                            nickname = it.nickname,
                            email = it.email,
                            spot = it.spot,
                            profileImagePath = it.profileImagePath,
                        )
                    }
                }
                .onFailure {
                    reduce {
                        state.copy(
                            name = ERROR_MESSAGE_NAME,
                            nickname = ERROR_MESSAGE_NICKNAME,
                            email = ERROR_MESSAGE_EMAIL,
                            spot = ERROR_MESSAGE_SPOT,
                        )
                    }
                }
        }
    }

    companion object {
        const val ERROR_MESSAGE_NAME = "회원님의"
        const val ERROR_MESSAGE_NICKNAME = "정보를"
        const val ERROR_MESSAGE_EMAIL = "불러오는데"
        const val ERROR_MESSAGE_SPOT = "실패했습니다!"
    }
}
