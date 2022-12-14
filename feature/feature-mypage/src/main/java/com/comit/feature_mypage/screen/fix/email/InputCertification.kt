@file:OptIn(InternalCoroutinesApi::class)

package com.comit.feature_mypage.screen.fix.email

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixEmailSideEffect
import com.comit.feature_mypage.mvi.InputCertificationSideEffect
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import com.comit.navigator.SimTongScreen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

private const val EmailLength: Int = 6

@Stable
private val InputCertificationNumberHeight: Dp = 90.dp
private const val InputCertificationNumberTotalTime: Int = 180

private const val OneMinute: Int = 60
private const val OneSecondDelay: Long = 1000
private const val CheckDigit: Int = 10

private const val CertificationNotValid = "유효하지 않은 인증코드입니다"
private const val CertificationNotCorrect = "인증코드가 일치하지 않습니다"

private const val EmailFormError = "사용할 수 없는 이메일입니다"
private const val CheckEmailFail = "이메일 인증을 실패했습니다"
private const val SameEmailException = "이미 사용중인 이메일입니다"

private const val SendCertificationFail = "인증코드 재정송을 실패했습니다"

@Composable
fun InputCertificationScreen(
    navController: NavController,
    vm: InputCertificationViewModel = hiltViewModel(),
    fixEmailViewModel: FixEmailViewModel = hiltViewModel(),
    email: String,
) {
    val toast = rememberToast()

    val inputCertificationContainer = vm.container
    val inputCertificationState = inputCertificationContainer.stateFlow.collectAsState().value
    val inputCertificationEffect = inputCertificationContainer.sideEffectFlow

    val btnEnabled = inputCertificationState.code.length == EmailLength

    inputCertificationEffect.observeWithLifecycle() {
        when (it) {
            InputCertificationSideEffect.CertificationCorrect -> {
                vm.changeEmail(email = email)
            }
            InputCertificationSideEffect.CertificationNotValid -> {
                vm.inputErrMsgCode(msg = CertificationNotValid)
            }
            InputCertificationSideEffect.CertificationNotCorrect -> {
                vm.inputErrMsgCode(msg = CertificationNotCorrect)
            }
            InputCertificationSideEffect.ChangeEmailSuccess -> {
                navController.navigate(
                    route = SimTongScreen.MyPage.MAIN
                ) {
                    popUpTo(route = SimTongScreen.MyPage.INPUT_CERTIFICATION_NUMBER) {
                        inclusive = true
                    }
                    popUpTo(route = SimTongScreen.MyPage.FIX_EMAIL) {
                        inclusive = true
                    }
                }
            }
            InputCertificationSideEffect.EmailFormError -> {
                vm.inputErrMsgCode(msg = EmailFormError)
            }
            InputCertificationSideEffect.CheckEmailFail -> {
                vm.inputErrMsgCode(msg = CheckEmailFail)
            }
            InputCertificationSideEffect.SameEmailException -> {
                vm.inputErrMsgCode(msg = SameEmailException)
            }
        }
    }

    val fixEmailContainer = fixEmailViewModel.container
    val fixEmailEffect = fixEmailContainer.sideEffectFlow

    val leftTime = stringResource(id = R.string.left_time)

    var totalTime by remember { mutableStateOf(InputCertificationNumberTotalTime) }

    val minute = totalTime / OneMinute
    val second =
        if (CheckDigit <= totalTime % OneMinute) totalTime % OneMinute
        else "0" + totalTime % OneMinute

    LaunchedEffect(key1 = totalTime) {
        if (totalTime > 0) {
            delay(OneSecondDelay)
            totalTime -= 1
        } else {
            navController.popBackStack()
        }
    }

    fixEmailEffect.observeWithLifecycle() {
        when (it) {
            FixEmailSideEffect.SendCodeFinish -> {
                vm.inputMsgCode(msg = "")
                vm.inputErrMsgCode(msg = null)
                totalTime = InputCertificationNumberTotalTime
            }
            else -> {
                toast(message = SendCertificationFail)
            }
        }
    }

    FixBaseScreen(
        header = stringResource(id = R.string.certification_number_input),
        onPrevious = { navController.popBackStack() },
        btnText = stringResource(id = R.string.check),
        onNext = { vm.checkCertification(email = email, code = inputCertificationState.code) },
        btnEnabled = btnEnabled
    ) {

        Column() {
            Spacer(modifier = Modifier.height(16.dp))

            Box(modifier = Modifier.height(InputCertificationNumberHeight)) {
                SimTongTextField(
                    value = inputCertificationState.code,
                    onValueChange = {
                        vm.inputMsgCode(msg = it)
                        vm.inputErrMsgCode(msg = null)
                    },
                    title = stringResource(id = R.string.certification_number_6),
                    error = inputCertificationState.errCode
                )

                Body10(
                    text = "$leftTime $minute : $second",
                    color = SimTongColor.MainColor,
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentWidth(Alignment.End)
                        .wrapContentHeight(Alignment.Top)
                )
            }

            SimTongBigRoundButton(
                text = stringResource(id = R.string.resend),
                color = SimTongButtonColor.WHITE,
                onClick = {
                    fixEmailViewModel.sendEmailCode(
                        email = email,
                    )
                }
            )
        }
    }
}
