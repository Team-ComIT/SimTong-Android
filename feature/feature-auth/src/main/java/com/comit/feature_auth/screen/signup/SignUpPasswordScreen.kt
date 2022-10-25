package com.comit.feature_auth.screen.signup

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.common.domain.isPasswordFormat
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body9
import com.comit.feature_auth.mvi.signup.SignUpState
import com.comit.feature_auth.vm.SignUpViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Stable
private val TextFieldMargin: Int = 8

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

/**
 * TextField의 Offset을 계산합니다.
 */
private fun textFieldOffset(
    step: SignUpStep.InputPassword,
    currentStep: SignUpStep.InputPassword,
): Dp {
    return (abs(0.coerceAtMost(step.offsetIdx - currentStep.offsetIdx) * TextFieldMargin)).dp
}

@Composable
fun SignUpPasswordScreen(
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    val toNextBtnClicked = {
        when (state.signUpPasswordStep) {
            SignUpStep.InputPassword.Password -> viewModel.navigatePasswordStep(SignUpStep.InputPassword.CheckPassword)
            SignUpStep.InputPassword.CheckPassword -> toNext()
        }
    }

    val toPreviousBtnClicked = {
        when (state.signUpPasswordStep) {
            SignUpStep.InputPassword.CheckPassword -> viewModel.navigatePasswordStep(SignUpStep.InputPassword.Password)
            SignUpStep.InputPassword.Password -> toPrevious()
        }
    }

    val coroutineScope = rememberCoroutineScope()

    val passwordOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputPassword.Password,
            currentStep = state.signUpPasswordStep,
        )
    )

    val checkPasswordOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputPassword.CheckPassword,
            currentStep = state.signUpPasswordStep,
        )
    )

    var password by remember { mutableStateOf("") }
    var checkPassword by remember { mutableStateOf("") }

    val btnEnabled = {
        when (state.signUpPasswordStep) {
            SignUpStep.InputPassword.Password -> password.isNotEmpty()
            SignUpStep.InputPassword.CheckPassword -> checkPassword.isNotEmpty()
        }
    }

    BackHandler {
        coroutineScope.launch {
            toPreviousBtnClicked()
        }
    }


    SimTongSimpleLayout(
        topAppBar = {
            BigHeader(
                text = "회원가입",
            ) {
                toPreviousBtnClicked()
            }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = state.signUpPasswordStep == SignUpStep.InputPassword.CheckPassword,
                    enter = TextFieldEnterAnimation,
                ) {
                    SimTongTextField(
                        modifier = Modifier.offset(y = checkPasswordOffset),
                        value = checkPassword,
                        onValueChange = { checkPassword = it },
                        title = "비밀번호 재입력",
                        error = if (password == checkPassword) "비밀번호가 일치하지 않습니다." else null,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SimTongTextField(
                    modifier = Modifier.offset(y = passwordOffset),
                    value = password,
                    onValueChange = { password = it },
                    title = "비밀번호 입력",
                    error = if (isPasswordFormat(password) && password.isNotEmpty()) "영문과 숫자 그리고 특수 기호가 모두 포함되어야 합니다." else null,
                )

                Spacer(modifier = Modifier.height(24.dp))

                Body9(
                    text = "계정이 있으신가요? 로그인",
                    color = SimTongColor.Gray400,
                )
            }
        },
        bottomContent = {
            BigRedRoundButton(
                text = "닫음",
                round = 0.dp,
                enabled = btnEnabled(),
            ) {
                toNextBtnClicked()
            }
        }
    )
}