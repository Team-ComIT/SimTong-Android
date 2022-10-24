package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R

@Composable
fun FixPassword() {
    var isLastPage by remember { mutableStateOf(false) }
    val btnText =
        if (isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.next)

    var password by remember { mutableStateOf("") }

    val btnEnabled = password.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.password_fix),
        headerBackClick = { },
        btnText = btnText,
        btnClick = { },
        btnEnabled = btnEnabled
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = password,
            onValueChange = { password = it },
            title = stringResource(id = R.string.password_input),
        )
    }
}

@Preview
@Composable
fun PreviewFixPassword() {
    FixPassword()
}
