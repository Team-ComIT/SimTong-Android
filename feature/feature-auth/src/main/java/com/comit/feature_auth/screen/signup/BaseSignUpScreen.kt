package com.comit.feature_auth.screen.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.imePadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader

@Composable
fun BaseSignUpScreen(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    btnEnabled: Boolean,
    content: @Composable () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BigHeader(
            text = "회원가입",
        ) {
            onPrevious()
        }

        content()

        Spacer(modifier = Modifier.weight(1f))

        BigRedRoundButton(
            modifier = Modifier.imePadding(),
            text = "다음",
            round = 0.dp,
            enabled = btnEnabled,
        ) {
            onNext()
        }
    }
}