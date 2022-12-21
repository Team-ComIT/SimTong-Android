package com.comit.feature_auth.screen.signUp

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.SimTongSimpleLayout
import com.comit.common.format.isPasswordFormat
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
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
    signUpPasswordStep: SignUpStep.InputPassword,
    password: String,
    onPasswordChanged: (String) -> Unit,
    checkPassword: String,
    onCheckPasswordChanged: (String) -> Unit,
    navigatePage: (SignUpStep.InputPassword) -> Unit,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
    toSignIn: () -> Unit,
) {
    val toNextBtnClicked = {
        when (signUpPasswordStep) {
            SignUpStep.InputPassword.Password -> navigatePage(SignUpStep.InputPassword.CheckPassword)
            SignUpStep.InputPassword.CheckPassword -> toNext()
        }
    }

    val toPreviousBtnClicked = {
        when (signUpPasswordStep) {
            SignUpStep.InputPassword.CheckPassword -> navigatePage(SignUpStep.InputPassword.Password)
            SignUpStep.InputPassword.Password -> toPrevious()
        }
    }

    val coroutineScope = rememberCoroutineScope()

    val passwordOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputPassword.Password,
            currentStep = signUpPasswordStep,
        )
    )

    val checkPasswordOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputPassword.CheckPassword,
            currentStep = signUpPasswordStep,
        )
    )

    val btnEnabled = {
        when (signUpPasswordStep) {
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
                    visible = signUpPasswordStep == SignUpStep.InputPassword.CheckPassword,
                    enter = TextFieldEnterAnimation,
                ) {
                    SimTongTextField(
                        modifier = Modifier.offset(y = checkPasswordOffset),
                        value = checkPassword,
                        isPassword = true,
                        onValueChange = {
                            onCheckPasswordChanged(it)
                        },
                        title = stringResource(id = R.string.password_again),
                        error = if (password != checkPassword && checkPassword.isNotEmpty())
                            stringResource(id = R.string.error_message_password) else null,
                        keyboardType = KeyboardType.Password,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                SimTongTextField(
                    modifier = Modifier.offset(y = passwordOffset),
                    value = password,
                    isPassword = true,
                    onValueChange = {
                        onPasswordChanged(it)
                    },
                    title = stringResource(id = R.string.password_input),
                    error = if (!isPasswordFormat(password) && password.isNotEmpty()) stringResource(
                        id = R.string.password_format_message
                    ) else null,
                    keyboardType = KeyboardType.Password,
                )

                Spacer(modifier = Modifier.height(24.dp))

                UnderlineBody9(
                    text = stringResource(id = R.string.sign_in_induction_msg),
                    underlineText = listOf(
                        stringResource(id = R.string.sign_in)
                    ),
                    color = SimTongColor.Gray400,
                    onClick = {
                        toSignIn()
                    },
                )
            }
        },
        bottomContent = {
            SimTongBigRoundButton(
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
