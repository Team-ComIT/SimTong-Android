package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import com.comit.core_design_system.component.BigHeader
import com.comit.feature_mypage.R

@Composable
fun FixNickNameScreen(

){
    var nickName by remember { mutableStateOf("") }
    var nickNameError by remember { mutableStateOf(null) }
    val nickNameEnabled = nickName.isNotEmpty()

    FixBaseScreen(
        header = stringResource(id = R.string.nick_name_fix),
        headerBackClick = { },
        btnText = stringResource(id = R.string.nick_name_fix),
        btnClick = { },
        btnEnabled = nickNameEnabled
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixNickNameScreen(){
    FixNickNameScreen()
}