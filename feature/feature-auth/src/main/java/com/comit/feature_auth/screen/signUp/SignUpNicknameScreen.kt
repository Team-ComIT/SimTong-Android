package com.comit.feature_auth.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimImageUploadLayout
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.SignUpState
import com.comit.feature_auth.vm.SignUpViewModel
import kotlinx.coroutines.launch

@Composable
fun SignUpNicknameScreen(
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
    onError: (String) -> Unit,
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
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SimImageUploadLayout(
                    imageFile = { viewModel.changeProfileImg(it) },
                    onError = onError
                )

                Spacer(modifier = Modifier.height(16.dp))

                SimTongTextField(
                    title = stringResource(id = R.string.nickname),
                    value = state.nickname,
                    onValueChange = { viewModel.changeNickname(it) },
                )
            }
        },
        bottomContent = {
            SimTongBigRoundButton(
                modifier = Modifier.imePadding(),
                text = stringResource(id = R.string.next),
                round = 0.dp,
                enabled = state.nickname.isNotEmpty()
            ) {
                toNext()
            }
        }
    )
}
