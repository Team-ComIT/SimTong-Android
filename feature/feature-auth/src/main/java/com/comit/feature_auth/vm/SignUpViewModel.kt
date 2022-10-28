package com.comit.feature_auth.vm

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import com.comit.feature_auth.mvi.signup.SignUpSideEffect
import com.comit.feature_auth.mvi.signup.SignUpState
import com.comit.feature_auth.screen.signUp.SignUpStep
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {

    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun navigatePage(page: Int) = intent {
        reduce { state.copy(currentPage = page) }
    }

    fun navigateNameStep(step: SignUpStep.InputUserInfo) = intent {
        reduce { state.copy(signUpNameStep = step) }
    }

    fun navigatePasswordStep(step: SignUpStep.InputPassword) = intent {
        reduce { state.copy(signUpPasswordStep = step) }
    }

    fun changeEmail(email: String) = intent {
        reduce { state.copy(email = email) }
    }

    fun changeEmployeeNumber(employeeNumber: String) = intent {
        reduce { state.copy(employeeNumber = employeeNumber) }
    }

    fun changeName(name: String) = intent {
        reduce { state.copy(name = name) }
    }

    fun changeNickname(nickname: String) = intent {
        reduce { state.copy(nickname = nickname) }
    }

    fun changeProfileImg(image: Bitmap) = intent {
        reduce { state.copy(profileImg = image) }
    }

    fun changeVerifyCode(code: String) = intent {
        reduce { state.copy(verifyCode = code) }
    }
}
