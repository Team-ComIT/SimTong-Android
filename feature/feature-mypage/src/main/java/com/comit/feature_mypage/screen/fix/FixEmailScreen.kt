package com.comit.feature_mypage.screen.fix

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.util.lerp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Comment
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.feature_mypage.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.concurrent.thread
import kotlin.math.absoluteValue

@Stable
private val InputCertificationNumberTotalTime: Int = 180

private var email = mutableStateOf("")
private var certificationNumber = mutableStateOf("")
//private var totalTime = mutableStateOf(InputCertificationNumberTotalTime)

// TODO: 버튼 클릭 이벤트나 에러 메세지 표시지는 추가가 필요합니다/

@ExperimentalPagerApi
@Composable
internal fun FixEmailScreen(
    navController: NavController,
) {
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
            if(!isLastPage) {
                email.value = ""
                navController.popBackStack()
            } else{
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

        if(!isLastPage){
            Column() {
                InputEmailScreen(textTitle = textTitle)
            }
        }
        AnimatedVisibility(
            visible = isLastPage,
            enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)){
                -it
            } + fadeIn(
                animationSpec = tween(durationMillis = 500)
            ),
            exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
                -it
            } + fadeOut(
                animationSpec = tween(durationMillis = 500)
            )
        ) {
            Column() {
                InputCertificationNumber(
                    textTitle = textTitle,
                )
            }
        }
    }
}

@Composable
private fun InputEmailScreen(
    textTitle: String,
) {
    Spacer(modifier = Modifier.height(16.dp))

    SimTongTextField(
        value = email.value,
        onValueChange = { email.value = it },
        title = textTitle
    )
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

    Spacer(modifier = Modifier.height(16.dp))

    Box(modifier = Modifier.height(InputCertificationNumberHeight)) {
        SimTongTextField(
            value = certificationNumber.value,
            onValueChange = { certificationNumber.value = it },
            title = textTitle,
            keyboardType = KeyboardType.Number
        )

        Body10(
            text = stringResource(id = R.string.left_time) +
                " " +
                minute +
                " : " +
                second,
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

@ExperimentalPagerApi
@Composable
private fun ChangeScreen(
    isLastPage: Boolean,
    textTitle: String
) {
    // TODO: 화면 전환 애니메이션 추가해야됨 //
    // https://fornewid.medium.com/material-motion-for-jetpack-compose-d97ef2114b9c

    if (isLastPage) {
        AnimatedVisibility(
            visible = isLastPage,
            enter = slideInHorizontally(animationSpec = tween(durationMillis = 200)){
                -it / 3
            } + fadeIn(
                animationSpec = tween(durationMillis = 200)
            ),
            exit = slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
                -it/3
            } + fadeOut(
                animationSpec = tween(durationMillis = 200)
            )
        ) {
            InputCertificationNumber(
                textTitle = textTitle,
            )
        }
    } else {
        InputEmailScreen(textTitle = textTitle)
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
