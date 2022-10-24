package com.comit.feature_mypage.screen.fix

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.feature_mypage.R

var email = mutableStateOf("")
var certificationNumber = mutableStateOf("")


@Composable
fun FixEmailScreen(
){
    var emailError by remember { mutableStateOf(null) }

    var isLastPage by remember { mutableStateOf(false) }

    val headerText =
        if(isLastPage) stringResource(id = R.string.certification_number_input)
        else stringResource(id = R.string.email_fix)

    val btnText =
        if(isLastPage) stringResource(id = R.string.certification_number_get)
        else stringResource(id = R.string.check)

    val textTitle =
        if(isLastPage) stringResource(id = R.string.certification_number)
        else  stringResource(id = R.string.email_input)

    val btnEnabled =
        if(isLastPage) certificationNumber.value.isNotEmpty()
        else email.value.isNotEmpty()

    FixBaseScreen(
        header = headerText,
        headerBackClick = {
            isLastPage = false
        }, 
        btnText = btnText,
        btnClick = {
            isLastPage = true
        },
        btnEnabled = btnEnabled
    ) {
        if(isLastPage){
            InputCertificationNumber(textTitle = textTitle)
        }else{
            InputEmailScreen(textTitle = textTitle)
        }

    }
}

@Composable
fun InputEmailScreen(
    textTitle: String
){
    Spacer(modifier = Modifier.height(16.dp))

    SimTongTextField(
        value = email.value,
        onValueChange = { email.value = it },
        title = textTitle
    )
}

@Stable
val InputCertificationNumberHeight: Dp = 150.dp

@Composable
fun InputCertificationNumber(
    textTitle: String
){
    Spacer(modifier = Modifier.height(16.dp))

    Box(modifier = Modifier.height(InputCertificationNumberHeight)){
        SimTongTextField(
            value = certificationNumber.value,
            onValueChange = { certificationNumber.value = it },
            title = textTitle,
            keyboardType = KeyboardType.Number
        )
        Body10(
            text = stringResource(id = R.string.left_time),
            color = SimTongColor.MainColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Top)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixEmailScreen(){
    FixEmailScreen()
}