package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FindPasswordCheckUserScreen(
    employeeNumber: String,
    email: String,
    fieldErrEmployeeNumber: String?,
    fieldErrEmail: String?,
    inputEmployeeNum: (String) -> Unit,
    inputEmail: (String) -> Unit,
    checkAccountExist: () -> Unit,
) {
    val buttonEnabled = !(employeeNumber.isEmpty() || email.isEmpty())

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 40.dp)
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = employeeNumber,
            onValueChange = {
                inputEmployeeNum(it)
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.employee_number),
            error = fieldErrEmployeeNumber
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimTongTextField(
            value = email,
            onValueChange = {
                inputEmail(it)
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.eng_email),
            error = fieldErrEmail,
        )

        Spacer(modifier = Modifier.height(30.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.find_password),
            onClick = checkAccountExist,
            enabled = buttonEnabled
        )
    }
}
