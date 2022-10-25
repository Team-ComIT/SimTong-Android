package com.comit.feature_auth.screen.signup

import android.graphics.Bitmap
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.SimTongSimpleLayout
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.component.SimImageUpload
import java.io.File

@Composable
fun SignUpNicknameScreen(
    toPrevious: () -> Unit,
    toNext: () -> Unit,
    onError: (String) -> Unit,
) {
    var nickname by remember { mutableStateOf("") }
    var profileImg by remember { mutableStateOf<Bitmap?>(null) }

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
                verticalArrangement = Arrangement.Center,
            ) {
                Spacer(modifier = Modifier.height(8.dp))

                SimImageUpload(
                    imageFile = { profileImg = it },
                    onError = onError
                )

                Spacer(modifier = Modifier.height(16.dp))

                SimTongTextField(
                    title = "닉네임",
                    value = nickname,
                    onValueChange = { nickname = it },
                )
            }
        },
        bottomContent = {
            BigRedRoundButton(
                text = "다음",
                round = 0.dp,
                enabled = nickname.isNotEmpty()
            ) {
                toNext()
            }
        }
    )
}

@Preview
@Composable
fun PreviewSignUpNicknameScreen() {
   SignUpNicknameScreen(toPrevious = { /*TODO*/ }, toNext = { /*TODO*/ }, onError = {})
}