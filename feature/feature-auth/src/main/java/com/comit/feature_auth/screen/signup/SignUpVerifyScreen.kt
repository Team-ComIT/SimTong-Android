package com.comit.feature_auth.screen.signup

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body9
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
                text = "회원가입",
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
                    title = "이름"
                )

                Spacer(modifier = Modifier.height(24.dp))

                Body9(text = "계정이 있으신가요? 로그인")
            }
        },
        bottomContent = {
            BigRedRoundButton(
                modifier = Modifier.fillMaxWidth(),
                text = "다음",
                enabled = state.verifyCode.isNotEmpty(),
                round = 0.dp,
            ) {
                toNext()
            }
        },
    )
}