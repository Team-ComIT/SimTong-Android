package com.comit.feature_auth.screen.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body9
import kotlin.math.abs

@Stable
private val TextFieldMargin: Int = 8

fun textFieldOffset(
    step: SignUpStep.InputUserInfo,
    currentStep: SignUpStep.InputUserInfo,
): Dp {
    return (abs(0.coerceAtMost(step.offsetIdx - currentStep.offsetIdx) * TextFieldMargin)).dp
}

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

@Composable
fun SignUpNameScreen(
    navController: NavController,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    var signUpStep: SignUpStep.InputUserInfo by remember {
        mutableStateOf(SignUpStep.InputUserInfo.NAME)
    }

    val nextBtnClick = {
        when (signUpStep) {
            SignUpStep.InputUserInfo.NAME -> signUpStep = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> signUpStep = SignUpStep.InputUserInfo.EMAIL
            SignUpStep.InputUserInfo.EMAIL -> toNext()
        }
    }

    val nameOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.NAME,
            currentStep = signUpStep,
        )
    )
    val employeeNumberOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER,
            currentStep = signUpStep,
        )
    )
    val emailOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMAIL,
            currentStep = signUpStep,
        )
    )
    val bottomLoreOffset by animateDpAsState(
        targetValue = (signUpStep.offsetIdx * TextFieldMargin + 24).dp
    )


    var name by remember { mutableStateOf<String?>(null) }
    var employeeNumber by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf<String?>(null) }

    val btnEnabled = when (signUpStep) {
        SignUpStep.InputUserInfo.NAME -> name != null
        SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> employeeNumber != null
        SignUpStep.InputUserInfo.EMAIL -> email != null
    }

    BaseSignUpScreen(
        onPrevious = { /*TODO*/ },
        onNext = nextBtnClick,
        btnEnabled = btnEnabled,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 40.dp),
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = signUpStep.offsetIdx >= SignUpStep.InputUserInfo.EMAIL.offsetIdx,
                enter = TextFieldEnterAnimation
            ) {
                SimTongTextField(
                    modifier = Modifier.offset(y = emailOffset),
                    title = "이메일",
                    value = email ?: "",
                    onValueChange = { email = it },
                )
            }

            AnimatedVisibility(
                visible = signUpStep.offsetIdx >= SignUpStep.InputUserInfo.EMPLOYEE_NUMBER.offsetIdx,
                enter = TextFieldEnterAnimation
            ) {
                SimTongTextField(
                    modifier = Modifier.offset(y = employeeNumberOffset),
                    title = "사원번호",
                    value = employeeNumber ?: "",
                    onValueChange = { employeeNumber = it },
                )
            }

            SimTongTextField(
                modifier = Modifier.offset(y = nameOffset),
                title = "이름",
                value = name ?: "",
                onValueChange = { name = it },
            )

            Row(
                modifier = Modifier.offset(y = bottomLoreOffset),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Body9(
                    text = "계정이 있으신가요?",
                    color = SimTongColor.Gray500,
                )

                Body9(
                    modifier = Modifier.padding(start = 3.dp),
                    text = "로그인",
                    color = SimTongColor.Gray500,
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUp() {
    val navController = rememberNavController()

    SignUpNameScreen(
        navController = navController,
        toPrevious = { },
    ) {

    }

}