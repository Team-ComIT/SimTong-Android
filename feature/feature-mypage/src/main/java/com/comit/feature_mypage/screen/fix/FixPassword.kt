package com.comit.feature_mypage.screen.fix

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R
import kotlin.math.abs

@Stable
private val TextFieldMargin: Int = 8

internal fun textFieldOffset(
    step: Int,
    currentStep: Int,
): Dp {
    return (abs(0.coerceAtMost(step - currentStep) * TextFieldMargin)).dp
}

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

@Composable
fun FixPassword() {
    var isLastPage by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    var passwordError by remember { mutableStateOf<String?>(null) }
    var passwordCheck by remember { mutableStateOf("") }
    var passwordCheckError by remember { mutableStateOf<String?>(null) }

    var fixPasswordStep by remember { mutableStateOf(1) }
    val btnNext = {
        when (fixPasswordStep) {
            1 -> fixPasswordStep = 2
            2 -> {}
        }
    }
    val btnBack = {
        when (fixPasswordStep) {
            1 -> {}
            2 -> fixPasswordStep = 1
        }
    }
    val passwordOffset by animateDpAsState(
        textFieldOffset(
            step = 1,
            currentStep = fixPasswordStep
        )
    )
    val passwordCheckOffset by animateDpAsState(
        textFieldOffset(
            step = 2,
            currentStep = fixPasswordStep
        )
    )

    val btnText =
        if (isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.next)

    val btnEnabled = password.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.password_fix),
        headerBackClick = { btnBack() },
        btnText = btnText,
        btnClick = { btnNext() },
        btnEnabled = btnEnabled
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = fixPasswordStep >= 2,
            enter = TextFieldEnterAnimation
        ) {
            SimTongTextField(
                value = passwordCheck,
                onValueChange = { passwordCheck = it },
                title = stringResource(id = R.string.password_input_again),
                error = passwordCheckError,
                modifier = Modifier.offset(y = passwordCheckOffset)
            )
        }

        SimTongTextField(
            value = password,
            onValueChange = { password = it },
            title = stringResource(id = R.string.password_input),
            error = passwordError,
            modifier = Modifier.offset(y = passwordOffset)
        )
    }
}

@Preview
@Composable
fun PreviewFixPassword() {
    FixPassword()
}
