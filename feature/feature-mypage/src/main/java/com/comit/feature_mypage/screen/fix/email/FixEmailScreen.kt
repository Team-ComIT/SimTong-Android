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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.util.AnimatedScreenSlide
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixEmailSideEffect
import com.comit.feature_mypage.mvi.FixEmailState
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import com.comit.navigator.SimTongScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@Stable
private val InputCertificationNumberTotalTime: Int = 180

// TODO: 에러 메세지 표시지는 추가가 필요합니다/
private const val SendCodeFinish = "인증 코드 전송을 성공하였습니다."
private const val EmailNotCorrect = "이메일 형식이 올바르지 않습니다."
private const val TooManyRequestsException =
    "e-mail 인증코드는 30분 최대 5회 발급 가능합니다.\n" +
        "잠시뒤 다시 시도해주세요."
private const val SameEmailException = "이미 사용중인 이메일입니다."

@OptIn(InternalCoroutinesApi::class)
@Composable
internal fun FixEmailScreen(
    navController: NavController,
    vm: FixEmailViewModel = hiltViewModel(),
) {
    val fixEmailContainer = vm.container
    val fixEmailState = fixEmailContainer.stateFlow.collectAsState().value
    val fixEmailEffect = fixEmailContainer.sideEffectFlow

    val toast = rememberToast()

    fixEmailEffect.observeWithLifecycle {
        when (it) {
            FixEmailSideEffect.SendCodeFinish -> {
                toast(message = SendCodeFinish)
                navController.navigate(
                    route = SimTongScreen.MyPage.INPUT_CERTIFICATION_NUMBER + "email${fixEmailState.email}"
                )
            }
            FixEmailSideEffect.EmailNotCorrect -> {
                vm.inputErrMsgEmail(msg = EmailNotCorrect)
            }
            FixEmailSideEffect.TooManyRequestsException -> {
                vm.inputErrMsgEmail(msg = TooManyRequestsException)
            }
            FixEmailSideEffect.SameEmailException -> {
                vm.inputErrMsgEmail(msg = SameEmailException)
            }
        }
    }

    val btnEnabled = fixEmailState.email.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.email_fix),
        onPrevious = {
            navController.popBackStack()
        },
        btnText = stringResource(id = R.string.certification_number_get),
        onNext = {
            vm.sendEmailCode(
                email = fixEmailState.email
            )
        },
        btnEnabled = btnEnabled
    ) {
        Column() {
            Spacer(modifier = Modifier.height(16.dp))

            SimTongTextField(
                value = fixEmailState.email,
                onValueChange = {
                    vm.inputMsgEmail(msg = it)
                    vm.inputErrMsgEmail(msg = null)
                },
                title = stringResource(id = R.string.email_input),
                error = fixEmailState.errEmail
            )
        }
    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewFixEmailScreen() {
    FixEmailScreen(
        navController = rememberNavController()
    )
}
