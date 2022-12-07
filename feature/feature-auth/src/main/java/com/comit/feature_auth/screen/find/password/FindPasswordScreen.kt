package com.comit.feature_auth.screen.find.password

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.comit.core.observeWithLifecycle
import com.comit.feature_auth.mvi.FindPasswordSideEffect
import com.comit.feature_auth.vm.FindPasswordViewModel
import kotlinx.coroutines.InternalCoroutinesApi

private const val FindPasswordCheckUserScreen = 0
private const val FindPasswordEmailCodeScreen = 1
private const val FindPasswordFixPasswordScreen = 2

private const val UserNotFound = "입력하신 정보가 올바르지 않습니다"
private const val EmailValidError = "올바른 이메일 형식을 입력해주세요"
private const val EmployeeNumNotNum = "사원번호가 올바르지 않은 형태입니다"

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindPasswordScreen(
    onPrevious: () -> Unit,
    findPasswordViewModel: FindPasswordViewModel = hiltViewModel(),
) {
    val container = findPasswordViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    sideEffect.observeWithLifecycle {
        when (it) {
            is FindPasswordSideEffect.UserNotFound -> {
                findPasswordViewModel.inputEmail(UserNotFound)
            }
            is FindPasswordSideEffect.UserIsAlready -> {
                findPasswordViewModel.navigatePage(FindPasswordEmailCodeScreen)
            }
            is FindPasswordSideEffect.NavigateToSignIn -> {
                onPrevious()
            }
            is FindPasswordSideEffect.EmailValidError -> {
                findPasswordViewModel.inputFieldErrEmail(EmailValidError)
            }
            is FindPasswordSideEffect.EmployeeNumNotNum -> {
                findPasswordViewModel.inputFieldErrEmployeeNum(EmployeeNumNotNum)
            }
        }
    }

    Crossfade(targetState = state.pageState) { pageState ->
        when (pageState) {
            FindPasswordCheckUserScreen -> FindPasswordCheckUserScreen(
                employeeNumber = state.employeeNumber,
                email = state.email,
                fieldErrEmail = state.fieldErrEmail,
                fieldErrEmployeeNumber = state.fieldErrEmployeeNumber,
                inputEmail = { findPasswordViewModel.inputEmail(it) },
                inputEmployeeNum = { findPasswordViewModel.inputEmployeeNum(it) },
                checkAccountExist = {
                    findPasswordViewModel.checkAccountExist(
                        employeeNum = state.employeeNumber,
                        email = state.email,
                    )
                }
            )
            FindPasswordEmailCodeScreen -> FindPasswordEmailCodeScreen()
            FindPasswordFixPasswordScreen -> FindPasswordFixPasswordScreen()
        }
    }
}
