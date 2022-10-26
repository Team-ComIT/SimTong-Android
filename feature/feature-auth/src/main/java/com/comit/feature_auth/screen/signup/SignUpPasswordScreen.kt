package com.comit.feature_auth.screen.signup

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.common.domain.format.isPasswordFormat
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
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
                text = stringResource(id = R.string.sign_up),
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
                        isPassword = true,
                        onValueChange = { checkPassword = it },
                        title = stringResource(id = R.string.password_again),
                        error = if (password == checkPassword)
                            stringResource(id = R.string.error_message_password)
                        else null,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SimTongTextField(
                    modifier = Modifier.offset(y = passwordOffset),
                    value = password,
                    isPassword = true,
                    onValueChange = { password = it },
                    title = stringResource(id = R.string.password_input),
                    error = if (isPasswordFormat(password) && password.isNotEmpty()) stringResource(
                        id = R.string.password_format_message
                    ) else null,
                )

                Spacer(modifier = Modifier.height(24.dp))

                UnderlineBody9(
                    text = stringResource(id = R.string.account_exist_message),
                    underlineText = listOf(
                        stringResource(id = R.string.sign_in)
                    ),
                    color = SimTongColor.Gray400,
                )
            }
        },
        bottomContent = {
            BigRedRoundButton(
                modifier = Modifier.imePadding(),
                text = stringResource(id = R.string.next),
                round = 0.dp,
                enabled = btnEnabled(),
            ) {
                toNextBtnClicked()
            }
        }
    )
}