package com.comit.feature_auth.screen.signup

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.Body9
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun SignUpVerifyScreen(
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    var verifyCode by remember { mutableStateOf("") }
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
                    value = verifyCode,
                    onValueChange = { verifyCode = it },
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
                enabled = verifyCode.isNotEmpty(),
                round = 0.dp,
            ) {
                toNext()
            }
        },
    )
}

@Preview
@Composable
fun PreviewSignUpVerifyScreen() {
    SignUpVerifyScreen(toPrevious = { /*TODO*/ }) {

    }
}
