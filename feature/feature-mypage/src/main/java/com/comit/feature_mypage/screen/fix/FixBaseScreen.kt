package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader

@Composable
fun FixBaseScreen(
    header: String,
    headerBackClick: () -> Unit,
    btnText: String,
    btnClick: () -> Unit,
    btnEnabled: Boolean,
    screenBody:@Composable ()->Unit
){
    Column(modifier = Modifier.fillMaxSize()) {
        BigHeader(
            text = header,
            onPrevious = headerBackClick
        )

        screenBody()

        BigRedRoundButton(
            text = btnText,
            onClick = btnClick,
            enabled = btnEnabled,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
        )
    }
}