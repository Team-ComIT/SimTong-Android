package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FindPasswordFixPasswordScreen(
    newPassword: String,
    onNewPasswordChanged: (String) -> Unit,
    newPasswordCheck: String,
    onNewPasswordCheckChanged: (String) -> Unit,
    fieldErrNewPassword: String?,
    fieldErrNewPasswordCheck: String?,
    fixPassword: () -> Unit,
) {

    val buttonEnabled = newPassword.isNotEmpty() && newPasswordCheck.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 40.dp,
            ),
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        SimTongTextField(
            value = newPassword,
            onValueChange = {
                onNewPasswordChanged(it)
            },
            isPassword = true,
            error = fieldErrNewPassword,
            hint = stringResource(id = R.string.new_password),
        )

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = newPasswordCheck,
            onValueChange = {
                onNewPasswordCheckChanged(it)
            },
            isPassword = true,
            error = fieldErrNewPasswordCheck,
            hint = stringResource(id = R.string.new_password_again),
        )

        Spacer(modifier = Modifier.height(30.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.next),
            onClick = {
                fixPassword()
            },
            enabled = buttonEnabled,
        )
    }
}
