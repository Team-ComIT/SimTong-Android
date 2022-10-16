package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FindPassword() {
    var employeeNum by remember { mutableStateOf<String?>(null) }
    var eMail by remember { mutableStateOf<String?>(null) }
    var employeeNumError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(employeeNum.isNullOrEmpty()|| eMail.isNullOrEmpty())

    val sideBtnBackgroundColor =
        if (eMail.isNullOrEmpty()) SimTongColor.Gray300 else SimTongColor.MainColor
    val sideBtnDisabledBackgroundColor =
        if (eMail.isNullOrEmpty()) SimTongColor.Gray300 else SimTongColor.MainColor
    val sideBtnPressedBackgroundColor =
        if (eMail.isNullOrEmpty()) SimTongColor.Gray400 else SimTongColor.MainColor100

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
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
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
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.email),
            error = emailError,
            sideBtnText = stringResource(id = R.string.certification),
            enabledSideBtn = true,
            sideBtnBackgroundColor = sideBtnBackgroundColor,
            sideBtnDisabledBackgroundColor = sideBtnDisabledBackgroundColor,
            sideBtnPressedBackgroundColor = sideBtnPressedBackgroundColor
        )

        Spacer(modifier = Modifier.height(30.dp))

        BigRedRoundButton(
            text = stringResource(id = R.string.find_password),
            onClick = {
                employeeNumError = ""
                emailError = ""
            },
            enabled = buttonEnabled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFindPasswordScreen() {
    FindPassword()
}
