package com.comit.feature_mypage.screen.fix.password

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixPasswordInSideEffect
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import kotlinx.coroutines.InternalCoroutinesApi
import kotlin.math.abs

enum class FixPasswordStep(
    val index: Int,
) {
    OLD_PASSWORD(
        index = 1
    ),
    PASSWORD(
        index = 2,
    ),
    CHECK_PASSWORD(
        index = 3,
    )
}

private const val TextFieldMargin: Int = 8

internal fun textFieldOffset(
    step: Int,
    currentStep: Int,
): Dp {
    return (abs(0.coerceAtMost(step - currentStep) * TextFieldMargin)).dp
}

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

private const val OldPasswordNotCorrect = "비밀번호가 일치하지 않습니다."

private const val FixPasswordFail = "사용할 수 없는 비밀번호입니다."

@OptIn(InternalCoroutinesApi::class)
@Composable
internal fun FixPassword(
    navController: NavController,
    vm: FixPasswordViewModel = hiltViewModel(),
) {
    val fixPassWordContainer = vm.container
    val fixPasswordInState = fixPassWordContainer.stateFlow.collectAsState().value
    val fixPasswordInSideEffect = fixPassWordContainer.sideEffectFlow

    var isLastPage by remember { mutableStateOf(false) }

    var passwordCheck by remember { mutableStateOf("") }

    var fixPasswordStep by remember { mutableStateOf(FixPasswordStep.OLD_PASSWORD) }

    fixPasswordInSideEffect.observeWithLifecycle() {
        when (it) {
            FixPasswordInSideEffect.OldPasswordCorrect -> {
                fixPasswordStep = FixPasswordStep.PASSWORD
            }
            FixPasswordInSideEffect.OldPasswordNotCorrect -> {
                vm.inPutErrOldPassword(msg = OldPasswordNotCorrect)
            }
            FixPasswordInSideEffect.FixPasswordSuccess -> {
                navController.popBackStack()
            }
            FixPasswordInSideEffect.FixPasswordFail -> {
                vm.inPutErrPassword(msg = FixPasswordFail)
            }
        }
    }

    val btnNext = {
        when (fixPasswordStep) {
            FixPasswordStep.OLD_PASSWORD ->
                vm.checkOldPassword(oldPassword = fixPasswordInState.oldPassword)
            FixPasswordStep.PASSWORD -> {
                isLastPage = true
                fixPasswordStep = FixPasswordStep.CHECK_PASSWORD
            }
            FixPasswordStep.CHECK_PASSWORD ->
                vm.fixPassword(
                    password = fixPasswordInState.oldPassword,
                    newPassword = fixPasswordInState.password,
                )
        }
    }
    val btnBack = {
        when (fixPasswordStep) {
            FixPasswordStep.OLD_PASSWORD -> navController.popBackStack()
            FixPasswordStep.PASSWORD -> fixPasswordStep = FixPasswordStep.OLD_PASSWORD
            FixPasswordStep.CHECK_PASSWORD -> {
                isLastPage = false
                fixPasswordStep = FixPasswordStep.PASSWORD
            }
        }
    }
    val passwordOldOffset by animateDpAsState(
        textFieldOffset(
            step = FixPasswordStep.OLD_PASSWORD.index,
            currentStep = fixPasswordStep.index
        )
    )
    val passwordOffset by animateDpAsState(
        textFieldOffset(
            step = FixPasswordStep.PASSWORD.index,
            currentStep = fixPasswordStep.index,
        )
    )
    val passwordCheckOffset by animateDpAsState(
        textFieldOffset(
            step = FixPasswordStep.CHECK_PASSWORD.index,
            currentStep = fixPasswordStep.index,
        )
    )

    val btnText =
        if (isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.next)

    val btnEnabled =
        when (fixPasswordStep) {
            FixPasswordStep.OLD_PASSWORD -> fixPasswordInState.oldPassword.isNotEmpty()
            FixPasswordStep.PASSWORD -> fixPasswordInState.password.isNotEmpty() && fixPasswordInState.oldPassword.isNotEmpty()
            FixPasswordStep.CHECK_PASSWORD ->
                passwordCheck == fixPasswordInState.password
                        && fixPasswordInState.password.isNotEmpty()
                        && fixPasswordInState.oldPassword.isNotEmpty()
        }

    FixBaseScreen(
        header = stringResource(id = R.string.password_fix),
        onPrevious = { btnBack() },
        btnText = btnText,
        onNext = { btnNext() },
        btnEnabled = btnEnabled,
    ) {
        Column() {
            Spacer(modifier = Modifier.height(16.dp))

            AnimatedVisibility(
                visible = fixPasswordStep.index >= FixPasswordStep.CHECK_PASSWORD.index,
                enter = TextFieldEnterAnimation,
            ) {
                SimTongTextField(
                    modifier = Modifier.offset(y = passwordCheckOffset),
                    isPassword = true,
                    value = passwordCheck,
                    onValueChange = { passwordCheck = it },
                    title = stringResource(id = R.string.password_input_again),
                    error = if(fixPasswordInState.errMsgPassword != null) "" else null,
                )
            }

            AnimatedVisibility(
                visible = fixPasswordStep.index >= FixPasswordStep.PASSWORD.index,
                enter = TextFieldEnterAnimation
            ) {
                SimTongTextField(
                    modifier = Modifier.offset(y = passwordOffset),
                    isPassword = true,
                    value = fixPasswordInState.password,
                    onValueChange = {
                        vm.inPutPassword(it)
                        vm.inPutErrPassword(msg = null)
                    },
                    title = stringResource(id = R.string.password_input),
                    error = fixPasswordInState.errMsgPassword,
                )
            }

            SimTongTextField(
                modifier = Modifier.offset(y = passwordOldOffset),
                isPassword = true,
                value = fixPasswordInState.oldPassword,
                onValueChange = {
                    vm.inPutOldPassword(it)
                    vm.inPutErrOldPassword(msg = null)
                },
                title = stringResource(id = R.string.password_old_input),
                error = fixPasswordInState.errMsgOldPassword,
            )
        }
    }
}

@Preview
@Composable
fun PreviewFixPassword() {
    FixPassword(
        navController = rememberNavController()
    )
}
