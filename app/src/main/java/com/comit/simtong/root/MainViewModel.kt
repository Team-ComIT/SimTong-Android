package com.comit.simtong.root

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.users.SaveDeviceTokenUseCase
import com.comit.simtong.firebase.getDeviceToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * TODO(limsaehyun)
 *
 * Device 토큰을 google-service 가 위치한 app 에서 추출하여 저장하기 위해
 * MainViewModel 이 존재함
 * app 모듈에 viewModel 이 존재해도 될까? app 모듈의 범위에 대해서 고민한 필요가 있음
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveDeviceTokenUseCase: SaveDeviceTokenUseCase,
) : ViewModel() {

    fun saveDeviceToken() {
        viewModelScope.launch {
            saveDeviceTokenUseCase(getDeviceToken())
        }
    }
}
