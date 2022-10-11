package com.comit.feature_auth.screen

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
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_auth.R

@Composable
fun FindEmployeeNum(

){
    var name by remember { mutableStateOf<String?>(null) }
    var workPlace by remember { mutableStateOf<String?>(null) }
    var eMail by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var workPlaceError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(name == null || name == "" || workPlace == null || workPlace == "" || eMail == null || eMail == "")

    val errorMsg = stringResource(id = R.string.error_message)

    Column(
        modifier = Modifier.padding(start = 40.dp, end = 40.dp)
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = name ?: "",
            onValueChange = {
                name = it
                nameError = null
                workPlaceError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.name),
            error = nameError
        )
        
        Spacer(modifier = Modifier.height(20.dp))

        SimTongTextField(
            value = workPlace ?: "",
            onValueChange = {
                workPlace = it
                nameError = null
                workPlaceError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.choose_work_place),
            error = workPlaceError
        )

        Spacer(modifier = Modifier.height(20.dp))

        SimTongTextField(
            value = eMail ?: "",
            onValueChange = {
                eMail = it
                nameError = null
                workPlaceError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.email),
            error = emailError
        )

        Spacer(modifier = Modifier.height(30.dp))


        BigRedRoundButton(
            text = stringResource(id = R.string.find_employee),
            onClick = {
                nameError = ""
                workPlaceError = ""
                emailError = errorMsg
            },
            enabled = buttonEnabled
        )
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewFindEmployeeNumScreen() {
    FindEmployeeNum()
}