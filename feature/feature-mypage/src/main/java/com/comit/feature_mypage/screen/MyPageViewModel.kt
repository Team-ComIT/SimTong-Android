package com.comit.feature_mypage.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.common.convert.limitSize
import com.comit.common.unit.sizeInKb
import com.comit.common.unit.sizeInMb
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.usecase.users.ChangeProfileImageUseCase
import com.comit.domain.usecase.users.FetchUserInformationUseCase
import com.comit.feature_mypage.mvi.MyPageSideEffect
import com.comit.feature_mypage.mvi.MyPageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.io.File
import javax.inject.Inject

internal const val ImageLimitSizeInKB: Int = 1024

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val fetchUserInformationUseCase: FetchUserInformationUseCase,
    private val changeProfileImageUseCase: ChangeProfileImageUseCase,
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
                            name = EmailException.ERROR_MESSAGE_NAME,
                            nickname = EmailException.ERROR_MESSAGE_NICKNAME,
                            email = EmailException.ERROR_MESSAGE_EMAIL,
                            spot = EmailException.ERROR_MESSAGE_SPOT,
                        )
                    }
                }
        }
    }

    fun changeProfileImage(
        profileImg: File,
    ) = intent {
        if(profileImg.sizeInKb > ImageLimitSizeInKB) {
            postSideEffect(MyPageSideEffect.LimitSize(profileImg.sizeInKb))
            return@intent
        }

        viewModelScope.launch {
            changeProfileImageUseCase(
                profileImg = profileImg,
            ).onFailure {
                when (it) {
                    is NeedLoginException -> throw it
                }
            }
        }
    }

    private object EmailException {
        const val ERROR_MESSAGE_NAME = "회원님의"
        const val ERROR_MESSAGE_NICKNAME = "정보를"
        const val ERROR_MESSAGE_EMAIL = "불러오는데"
        const val ERROR_MESSAGE_SPOT = "실패했습니다!"
    }
}
