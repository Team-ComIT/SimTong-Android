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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import kotlin.math.abs

enum class FixPasswordStep(
    val index: Int,
) {
    PASSWORD(
        index = 1,
    ),
    CHECK_PASSWORD(
        index = 2,
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

@Composable
internal fun FixPassword(
    navController: NavController,
) {
    val isLastPage by remember { mutableStateOf(false) }

    var password by remember { mutableStateOf("") }
    val passwordError by remember { mutableStateOf<String?>(null) }
    var passwordCheck by remember { mutableStateOf("") }
    val passwordCheckError by remember { mutableStateOf<String?>(null) }

    var fixPasswordStep by remember { mutableStateOf(FixPasswordStep.PASSWORD) }

    val btnNext = {
        when (fixPasswordStep) {
            FixPasswordStep.PASSWORD -> fixPasswordStep = FixPasswordStep.CHECK_PASSWORD
            FixPasswordStep.CHECK_PASSWORD -> navController.popBackStack()
        }
    }
    val btnBack = {
        when (fixPasswordStep) {
            FixPasswordStep.PASSWORD -> navController.popBackStack()
            FixPasswordStep.CHECK_PASSWORD -> fixPasswordStep = FixPasswordStep.PASSWORD
        }
    }
    val passwordOffset by animateDpAsState(
        textFieldOffset(
            step = 1,
            currentStep = fixPasswordStep.index,
        )
    )
    val passwordCheckOffset by animateDpAsState(
        textFieldOffset(
            step = 2,
            currentStep = fixPasswordStep.index,
        )
    )

    val btnText =
        if (isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.next)

    val btnEnabled = password.isNotEmpty()

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
                visible = fixPasswordStep.index >= 2,
                enter = TextFieldEnterAnimation,
            ) {
                SimTongTextField(
                    modifier = Modifier.offset(y = passwordCheckOffset),
                    value = passwordCheck,
                    onValueChange = { passwordCheck = it },
                    title = stringResource(id = R.string.password_input_again),
                    error = passwordCheckError,
                )
            }

            SimTongTextField(
                modifier = Modifier.offset(y = passwordOffset),
                value = password,
                onValueChange = { password = it },
                title = stringResource(id = R.string.password_input),
                error = passwordError,
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
