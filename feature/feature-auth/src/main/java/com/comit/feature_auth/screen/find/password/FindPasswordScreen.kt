package com.comit.feature_auth.screen.find.password

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.rememberToast
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

private const val EmailVerifyAlready = "이미 인증된 이메일입니다"
private const val TooManyRequest = "인증 요청 횟수를 초과하였습니다"

private const val EmailCodeNotCorrect = "유효하지 않는 인증코드입니다"

private const val SuccessFixPassword = "비밀번호 변경이 완료되었습니다."

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindPasswordScreen(
    navController: NavController,
    findPasswordViewModel: FindPasswordViewModel = hiltViewModel(),
) {
    val container = findPasswordViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val toast = rememberToast()

    sideEffect.observeWithLifecycle {
        when (it) {
            is FindPasswordSideEffect.UserNotFound -> {
                findPasswordViewModel.inputFieldErrEmail(UserNotFound)
            }
            is FindPasswordSideEffect.UserIsAlready -> {
                findPasswordViewModel.sendEmailCode()
                findPasswordViewModel.navigatePage(FindPasswordEmailCodeScreen)
            }
            is FindPasswordSideEffect.EmailValidError -> {
                findPasswordViewModel.inputFieldErrEmail(EmailValidError)
            }
            is FindPasswordSideEffect.EmployeeNumNotNum -> {
                findPasswordViewModel.inputFieldErrEmployeeNum(EmployeeNumNotNum)
            }
            is FindPasswordSideEffect.EmailVerifyAlready -> {
                toast(
                    message = EmailVerifyAlready,
                )
            }
            is FindPasswordSideEffect.TooManyRequest -> {
                toast(
                    message = TooManyRequest,
                )
            }
            is FindPasswordSideEffect.NavigateToFixPassword -> {
                findPasswordViewModel.navigatePage(FindPasswordFixPasswordScreen)
            }
            is FindPasswordSideEffect.EmailCodeNotCorrect -> {
                findPasswordViewModel.inputFieldErrEmailCode(EmailCodeNotCorrect)
            }
            is FindPasswordSideEffect.NavigateToSignIn -> {
                toast(
                    message = SuccessFixPassword,
                )
                navController.popBackStack()
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
                inputEmail = {
                    findPasswordViewModel.inputEmail(it)
                },
                inputEmployeeNum = {
                    findPasswordViewModel.inputEmployeeNum(it)
                },
                checkAccountExist = {
                    findPasswordViewModel.checkAccountExist(
                        employeeNum = state.employeeNumber,
                        email = state.email,
                    )
                },
            )
            FindPasswordEmailCodeScreen -> FindPasswordEmailCodeScreen(
                emailCode = state.emailCode,
                onEmailCodeChanged = {
                    findPasswordViewModel.inputEmailCode(it)
                },
                fieldErrEmailCode = state.fieldErrEmailCode,
                restartBtnEnabled = state.restartBtnEnabled,
                onRestartBtnEnabledChanged = {
                    findPasswordViewModel.changedRestartBtnEnabled(it)
                },
                sendEmailCode = {
                    findPasswordViewModel.sendEmailCode()
                },
                checkVerifyEmailCode = {
                    findPasswordViewModel.checkEmailCode(
                        email = state.email,
                        emailCode = state.emailCode,
                    )
                },
            )
            FindPasswordFixPasswordScreen -> FindPasswordFixPasswordScreen(
                newPassword = state.newPassword,
                onNewPasswordChanged = {
                    findPasswordViewModel.inputNewPassword(it)
                },
                newPasswordCheck = state.newPasswordCheck,
                onNewPasswordCheckChanged = {
                    findPasswordViewModel.inputNewPasswordCheck(it)
                },
                fieldErrNewPassword = state.fieldErrNewPassword,
                fieldErrNewPasswordCheck = state.fieldErrNewPasswordCheck,
                fixPassword = {
                    findPasswordViewModel.initializationPassword(
                        email = state.email,
                        employeeNumber = state.employeeNumber,
                        newPassword = state.newPassword,
                    )
                },
            )
        }
    }
}
