package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FixPasswordScreen() {
    var newPassword by remember { mutableStateOf("") }
    var newPasswordAgain by remember { mutableStateOf("") }
    var newPasswordError by remember { mutableStateOf<String?>(null) }
    var newPasswordAgainError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(newPassword == "" || newPasswordAgain == "")

    val errorMsg = stringResource(id = R.string.error_message)

    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        SimTongTextField(
            value = newPassword,
            onValueChange = {
                newPassword = it
                newPasswordError = null
                newPasswordAgainError = null
            },
            isPassword = true,
            error = newPasswordError,
            hint = stringResource(id = R.string.new_password)
        )

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = newPasswordAgain,
            onValueChange = {
                newPasswordAgain = it
                newPasswordError = null
                newPasswordAgainError = null
            },
            isPassword = true,
            error = newPasswordAgainError,
            hint = stringResource(id = R.string.new_password_again)
        )

        Spacer(modifier = Modifier.height(30.dp))

        BigRedRoundButton(
            text = stringResource(id = R.string.next),
            onClick = {
                newPasswordError = ""
                newPasswordAgainError = errorMsg
            },
            enabled = buttonEnabled
        )
    }
}

@Preview
@Composable
fun PreviewFixPassword() {
    FixPasswordScreen()
}
