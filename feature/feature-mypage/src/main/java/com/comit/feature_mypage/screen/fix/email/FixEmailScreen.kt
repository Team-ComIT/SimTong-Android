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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core.observeWithLifecycle
import com.comit.common.rememberToast
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.util.AnimatedScreenSlide
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixEmailSideEffect
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import com.comit.feature_mypage.mvi.FixEmailState
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@Stable
private val InputCertificationNumberTotalTime: Int = 180

private const val EmailLength: Int = 6

// TODO: 에러 메세지 표시지는 추가가 필요합니다/
private const val SendCodeFinish = "인증 코드 전송을 성공하였습니다."
private const val EmailTextErrorException = "이메일 형식이 올바르지 않습니다."
private const val TooManyRequestsException =
    "e-mail 인증코드는 30분 최대 5회 발급 가능합니다.\n" +
        "잠시뒤 다시 시도해주세요."
private const val SameEmailException = "이미 사용중인 이메일입니다."
private const val ServerException = "서버와 통신에 실패했습니다."
private const val NoInternetException = "인터넷 연결 상태를 확인해주세요."
private const val CheckCodeFail = "인증 코드가 알맞지 않습니다"

@OptIn(InternalCoroutinesApi::class)
@ExperimentalPagerApi
@Composable
internal fun FixEmailScreen(
    navController: NavController,
    vm: FixEmailViewModel = hiltViewModel(),
) {
    val fixEmailContainer = vm.container
    val fixEmailState = fixEmailContainer.stateFlow.collectAsState().value
    val fixEmailEffect = fixEmailContainer.sideEffectFlow

    var isLastPage by remember { mutableStateOf(false) }
    val toast = rememberToast()

    fixEmailEffect.observeWithLifecycle {
        when (it) {
            FixEmailSideEffect.SendCodeFinish -> {
                isLastPage = true
                toast(message = SendCodeFinish)
            }
            FixEmailSideEffect.EmailTextErrorException -> {
                vm.inputErrMsgEmail(msg = EmailTextErrorException)
            }
            FixEmailSideEffect.TooManyRequestsException -> {
                vm.inputErrMsgEmail(msg = TooManyRequestsException)
            }
            FixEmailSideEffect.SameEmailException -> {
                vm.inputErrMsgEmail(msg = SameEmailException)
            }
            FixEmailSideEffect.ServerException -> {
                vm.inputErrMsgEmail(msg = ServerException)
            }
            FixEmailSideEffect.NoInternetException -> {
                vm.inputErrMsgEmail(msg = NoInternetException)
            }
            FixEmailSideEffect.CheckCodeSuccess -> {
                navController.popBackStack()
            }
            FixEmailSideEffect.CheckCodeFail -> {
                vm.inputErrMsgCode(msg = CheckCodeFail)
            }
        }
    }

    val headerText =
        if (isLastPage) stringResource(id = R.string.certification_number_input)
        else stringResource(id = R.string.email_fix)

    val btnText =
        if (isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.certification_number_get)

    val textTitle =
        if (isLastPage) stringResource(id = R.string.certification_number)
        else stringResource(id = R.string.email_input)

    val btnEnabled =
        if (isLastPage) fixEmailState.code.length == EmailLength
        else fixEmailState.email.isNotEmpty()

    FixBaseScreen(
        header = headerText,
        onPrevious = {
            if (!isLastPage) {
                navController.popBackStack()
            } else {
                vm.inputMsgCode(msg = "")
                vm.inputErrMsgCode(msg = null)
            }
            isLastPage = !isLastPage
        },
        btnText = btnText,
        onNext = {
            if (isLastPage) {
                vm.checkEmailCode(
                    email = fixEmailState.email,
                    code = fixEmailState.code
                )
            } else {
                isLastPage = true
            }
        },
        btnEnabled = btnEnabled
    ) {

        if (!isLastPage) {
            InputEmailScreen(
                textTitle = textTitle,
                vm = vm,
                fixEmailState = fixEmailState
            )
        }

        AnimatedScreenSlide(visible = isLastPage) {
            InputCertificationNumber(
                textTitle = textTitle,
                vm = vm,
                fixEmailState = fixEmailState
            )
        }
    }
}

@Composable
private fun InputEmailScreen(
    textTitle: String,
    vm: FixEmailViewModel,
    fixEmailState: FixEmailState,
) {
    Column() {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = fixEmailState.email,
            onValueChange = {
                vm.inputMsgEmail(msg = it)
                vm.inputErrMsgEmail(msg = null)
            },
            title = textTitle,
            error = fixEmailState.errEmail
        )
    }
}

@Stable
private val InputCertificationNumberHeight: Dp = 90.dp

@Stable
private val OneMinute: Int = 60

@Stable
private val OneSecondDelay: Long = 1000

@Stable
private val CheckDigit: Int = 10

@Composable
internal fun InputCertificationNumber(
    textTitle: String,
    vm: FixEmailViewModel,
    fixEmailState: FixEmailState,
) {

    val leftTime = stringResource(id = R.string.left_time)

    var totalTime by remember {
        mutableStateOf(InputCertificationNumberTotalTime)
    }

    val minute = totalTime / OneMinute
    val second =
        if (CheckDigit <= totalTime % OneMinute) totalTime % OneMinute
        else "0" + totalTime % OneMinute

    LaunchedEffect(key1 = totalTime) {
        if (totalTime > 0) {
            delay(OneSecondDelay)
            totalTime -= 1
        }
    }

    Column() {
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.height(InputCertificationNumberHeight)) {
            SimTongTextField(
                value = fixEmailState.code,
                onValueChange = {
                    vm.inputMsgCode(msg = it)
                    vm.inputErrMsgCode(msg = null)
                },
                title = textTitle,
                keyboardType = KeyboardType.Number,
                error = fixEmailState.errCode
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
                vm.inputMsgCode(msg = "")
                vm.inputErrMsgCode(msg = null)
                totalTime = InputCertificationNumberTotalTime
            }
        )
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
