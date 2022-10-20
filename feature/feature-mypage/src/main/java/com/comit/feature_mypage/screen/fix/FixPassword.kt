package com.comit.feature_mypage.screen.fix

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.comit.feature_mypage.R

@Composable
fun FixPassword(

){
    val btnEnabled by remember { mutableStateOf(false) }
    val isLastPage by remember { mutableStateOf(false) }
    val btnText = 
        if(isLastPage) stringResource(id = R.string.check)
        else stringResource(id = R.string.next)
    
    FixBaseScreen(
        header = stringResource(id = R.string.password_fix),
        headerBackClick = { },
        btnText = btnText,
        btnClick = { },
        btnEnabled = btnEnabled
    ) {
        
    }
}

@Preview
@Composable
fun PreviewFixPassword(){
    FixPassword()
}