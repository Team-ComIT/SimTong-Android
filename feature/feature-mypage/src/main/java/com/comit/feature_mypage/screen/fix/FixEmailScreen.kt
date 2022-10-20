package com.comit.feature_mypage.screen.fix

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import com.comit.feature_mypage.R

@Composable
fun FixEmailScreen(
){
    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(null) }
    val emailEnabled = email.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.email_fix),
        headerBackClick = { },
        btnText = stringResource(id = R.string.email_fix),
        btnClick = { },
        btnEnabled = emailEnabled
    ) {

    }
}