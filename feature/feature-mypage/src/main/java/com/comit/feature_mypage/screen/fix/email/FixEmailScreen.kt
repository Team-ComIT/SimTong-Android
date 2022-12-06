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
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.util.AnimatedScreenSlide
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixEmailSideEffect
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@Stable
private val InputCertificationNumberTotalTime: Int = 180

private var email = mutableStateOf("")
private var certificationNumber = mutableStateOf("")

private const val EmailTextErrorException = ""
private const val EmailCertificationException = ""
private const val SameEmailException = ""
private const val ServerException = ""
private const val NoInternetException = ""

// TODO: 에러 메세지 표시지는 추가가 필요합니다/

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

    fixEmailEffect.observeWithLifecycle {
        when (it) {
            FixEmailSideEffect.FixEmailFinish -> {

            }
            FixEmailSideEffect.EmailTextErrorException -> {
                vm.inputErrMsgEmail(msg = EmailTextErrorException)
            }
            FixEmailSideEffect.EmailCertificationException -> {
                vm.inputErrMsgEmail(msg = EmailCertificationException)
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
        }
    }

    var emailError by remember { mutableStateOf<String?>(null) }

    var isLastPage by remember { mutableStateOf(false) }

    val headerText =
        if (isLastPage) stringResource(id = R.string.certification_number_input)
        else stringResource(id = R.string.email_fix)

    val btnText =
        if (isLastPage) stringResource(id = R.string.certification_number_get)
        else stringResource(id = R.string.check)

    val textTitle =
        if (isLastPage) stringResource(id = R.string.certification_number)
        else stringResource(id = R.string.email_input)

    val btnEnabled =
        if (isLastPage) certificationNumber.value.isNotEmpty()
        else email.value.isNotEmpty()

    FixBaseScreen(
        header = headerText,
        onPrevious = {
            if (!isLastPage) {
                email.value = ""
                navController.popBackStack()
            } else {
                certificationNumber.value = ""
            }
            isLastPage = !isLastPage
        },
        btnText = btnText,
        onNext = {
            emailError = ""
            isLastPage = true
        },
        btnEnabled = btnEnabled
    ) {

        if (!isLastPage) {
            InputEmailScreen(textTitle = textTitle)
        }

        AnimatedScreenSlide(visible = isLastPage) {
            InputCertificationNumber(textTitle = textTitle)
        }
    }
}

@Composable
private fun InputEmailScreen(
    textTitle: String,
) {
    Column() {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = email.value,
            onValueChange = { email.value = it },
            title = textTitle
        )
    }
}

@Stable
private val InputCertificationNumberHeight: Dp = 70.dp

@Stable
private val OneMinute: Int = 60

@Stable
private val OneSecondDelay: Long = 1000

@Stable
private val CheckDigit: Int = 10

@Composable
internal fun InputCertificationNumber(
    textTitle: String,
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
                value = certificationNumber.value,
                onValueChange = { certificationNumber.value = it },
                title = textTitle,
                keyboardType = KeyboardType.Number
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

        Spacer(modifier = Modifier.height(18.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.resend),
            color = SimTongButtonColor.WHITE,
            onClick = {
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
