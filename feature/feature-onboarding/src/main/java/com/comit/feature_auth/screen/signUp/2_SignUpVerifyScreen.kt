package com.comit.feature_auth.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.common.SimTongSimpleLayout
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import kotlinx.coroutines.launch

@Composable
fun SignUpVerifyScreen(
    toPrevious: () -> Unit,
    checkVerifyCode: () -> Unit,
    fieldErrEmailCode: String? = null,
    verifyCode: String,
    onVerifyCodeChanged: (String) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

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

                SimTongTextField(
                    value = verifyCode,
                    onValueChange = { onVerifyCodeChanged(it) },
                    title = stringResource(id = R.string.verify_code),
                    error = fieldErrEmailCode,
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
                modifier = Modifier.imePadding(),
                text = stringResource(id = R.string.next),
                enabled = verifyCode.isNotEmpty(),
                round = 0.dp,
            ) {
                checkVerifyCode()
            }
        },
    )
}
