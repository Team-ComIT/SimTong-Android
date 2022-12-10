@file:Suppress(
    "TooManyFunctions",
)

package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.common.SimCountDownTimer
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

private const val EmailCodeCountDownTime: Int = 180

@Composable
fun FindPasswordEmailCodeScreen(
    emailCode: String,
    onEmailCodeChanged: (String) -> Unit,
    fieldErrEmailCode: String?,
    restartBtnEnabled: Boolean,
    onRestartBtnEnabledChanged: (Boolean) -> Unit,
    sendEmailCode: () -> Unit,
    checkVerifyEmailCode: () -> Unit,
) {
    var totalTime by remember { mutableStateOf(EmailCodeCountDownTime) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 40.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))

            SimCountDownTimer(
                totalTime = totalTime,
                onTimeChanged = { totalTime = it },
                onFinished = {
                    onRestartBtnEnabledChanged(true)
                }
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        SimTongTextField(
            value = emailCode,
            onValueChange = { onEmailCodeChanged(it) },
            error = fieldErrEmailCode,
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.resend),
            enabled = restartBtnEnabled,
            color = SimTongButtonColor.WHITE,
        ) {
            totalTime = EmailCodeCountDownTime
            onRestartBtnEnabledChanged(false)

            sendEmailCode()
        }

        Spacer(modifier = Modifier.height(20.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.check),
            enabled = emailCode.isNotEmpty()
        ) {
            checkVerifyEmailCode()
        }
    }
}
