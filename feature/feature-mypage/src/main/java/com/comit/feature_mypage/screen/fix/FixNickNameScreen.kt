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
fun FixNickNameScreen() {
    var nickName by remember { mutableStateOf("") }
    var nickNameError by remember { mutableStateOf<String?>(null) }
    val nickNameEnabled = nickName.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.nick_name_input),
        onPrevious = { },
        btnText = stringResource(id = R.string.nick_name_fix),
        onNext = { nickNameError = "" },
        btnEnabled = nickNameEnabled
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = nickName,
            onValueChange = { nickName = it },
            title = stringResource(id = R.string.nick_name_input),
            error = nickNameError
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixNickNameScreen() {
    FixNickNameScreen()
}
