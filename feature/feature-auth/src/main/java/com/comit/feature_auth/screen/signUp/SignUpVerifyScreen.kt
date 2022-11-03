package com.comit.feature_auth.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.common.domain.unit.getMinute
import com.comit.common.domain.unit.getSecond
import com.comit.common.domain.unit.getStringTime
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.signup.SignUpState
import com.comit.feature_auth.vm.SignUpViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val DefaultVerifyTime: Int = 180
private const val DefaultTimeDelay: Long = 1000

@Composable
fun SignUpVerifyScreen(
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    var verifyTimeLimit by remember {
        mutableStateOf(DefaultVerifyTime)
    }

    val minute = getMinute(verifyTimeLimit)
    val second = getSecond(verifyTimeLimit)

    LaunchedEffect(key1 = verifyTimeLimit) {
        if (verifyTimeLimit > 0) {
            delay(DefaultTimeDelay)
            verifyTimeLimit -= 1
        }
    }

    
    BackHandler {
        coroutineScope.launch {
            toPrevious()
        }
    }

    SimTongSimpleLayout(
        topAppBar = {
            BigHeader(
                text = stringResource(id = R.string.sign_up),
            ) {
                toPrevious()
            }
        },
        content = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    Body10(
                        text = getStringTime(
                            minute = minute,
                            second = second,
                        ),
                        color = SimTongColor.Error,
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))

                SimTongTextField(
                    value = state.verifyCode,
                    onValueChange = { viewModel.changeVerifyCode(it) },
                    title = stringResource(id = R.string.certification),
                )

                Spacer(modifier = Modifier.height(24.dp))

                UnderlineBody9(
                    text = stringResource(id = R.string.sign_in_induction_msg),
                    underlineText = listOf(
                        stringResource(id = R.string.sign_in)
                    ),
                    color = SimTongColor.Gray400,
                )
            }
        },
        bottomContent = {
            SimTongBigRoundButton(
                modifier = Modifier
                    .imePadding(),
                text = stringResource(id = R.string.next),
                enabled = state.verifyCode.isNotEmpty(),
                round = 0.dp,
            ) {
                toNext()
            }
        },
    )
}
