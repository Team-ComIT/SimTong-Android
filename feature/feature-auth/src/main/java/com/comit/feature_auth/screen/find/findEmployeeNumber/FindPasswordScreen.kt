package com.comit.feature_auth.screen.find.findEmployeeNumber

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FindPasswordScreen() {
    var employeeNum by remember { mutableStateOf<String?>(null) }
    var eMail by remember { mutableStateOf<String?>(null) }
    var employeeNumError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(employeeNum.isNullOrEmpty() || eMail.isNullOrEmpty())

    val errorMsg = stringResource(id = R.string.error_message)

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 40.dp)
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = employeeNum ?: "",
            onValueChange = {
                employeeNum = it
                employeeNumError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.employee_number),
            error = employeeNumError
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimTongTextField(
            value = eMail ?: "",
            onValueChange = {
                eMail = it
                employeeNumError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.eng_email),
            error = emailError,
            sideBtnText = stringResource(id = R.string.certification),
            enabledSideBtn = true,
            simTongButtonColor = if (eMail.isNullOrEmpty()) SimTongButtonColor.GRAY else SimTongButtonColor.RED,
        )

        Spacer(modifier = Modifier.height(30.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.find_password),
            onClick = {
                employeeNumError = ""
                emailError = errorMsg
            },
            enabled = buttonEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFindPasswordScreen() {
    FindPasswordScreen()
}