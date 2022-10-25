package com.comit.feature_auth.screen.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.component.SimImageUpload
import com.comit.feature_auth.mvi.signup.SignUpState
import com.comit.feature_auth.vm.SignUpViewModel

@Composable
fun SignUpNicknameScreen(
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
    onError: (String) -> Unit,
) {

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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SimImageUpload(
                    imageFile = { viewModel.changeProfileImg(it) },
                    onError = onError
                )

                Spacer(modifier = Modifier.height(16.dp))

                SimTongTextField(
                    title = "닉네임",
                    value = state.nickname,
                    onValueChange = { viewModel.changeNickname(it) },
                )
            }
        },
        bottomContent = {
            BigRedRoundButton(
                text = "다음",
                round = 0.dp,
                enabled = state.nickname.isNotEmpty()
            ) {
                toNext()
            }
        }
    )
}