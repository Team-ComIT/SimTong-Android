package com.comit.feature_home.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.users.FetchUserInformationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchUserInformationUseCase: FetchUserInformationUseCase,
) : ViewModel() {

    fun fetchUsers() {
        viewModelScope.launch {
            println("home viewmodel")
            fetchUserInformationUseCase()
                .onSuccess { }
        }
    }
}
