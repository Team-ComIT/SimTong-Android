package com.comit.feature_mypage.screen

import androidx.lifecycle.ViewModel
import com.comit.domain.usecase.users.FetchUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val fetchUserInformationUseCase: FetchUserInformationUseCase
) : Container<>, ViewModel() {
}