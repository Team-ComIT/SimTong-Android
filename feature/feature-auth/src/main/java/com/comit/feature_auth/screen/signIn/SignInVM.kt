package com.comit.feature_auth.screen.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.comit.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInVM @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel() {

    fun signIn(
        employeeNumber: Int,
        password: String,
    ) {
        viewModelScope.launch {
            signInUseCase(
                params = SignInUseCase.Params(
                    employeeNumber = employeeNumber,
                    password = password
                ),
            ).onSuccess {
            }.onFailure {
            }
        }
    }
}
