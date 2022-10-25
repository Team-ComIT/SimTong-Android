package com.comit.feature_auth.vm

import androidx.lifecycle.ViewModel
import com.comit.feature_auth.mvi.signup.SignUpSideEffect
import com.comit.feature_auth.mvi.signup.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {

    override val container = container<SignUpState, SignUpSideEffect>(SignUpState())

    fun navigatePage(page: Int) = intent {
        reduce { state.copy(currentPage = page) }
    }

}