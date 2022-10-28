package com.comit.feature_auth.screen.signup

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
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.signup.SignUpState
import com.comit.feature_auth.vm.SignUpViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpVerifyScreen(
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
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
                    value = state.verifyCode,
                    onValueChange = { viewModel.changeVerifyCode(it) },
                    title = stringResource(id = R.string.name),
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
            SimTongBigRoundButton(
                modifier = Modifier.imePadding(),
                text = stringResource(id = R.string.next),
                enabled = state.verifyCode.isNotEmpty(),
                round = 0.dp,
            ) {
                toNext()
            }
        },
    )
}
