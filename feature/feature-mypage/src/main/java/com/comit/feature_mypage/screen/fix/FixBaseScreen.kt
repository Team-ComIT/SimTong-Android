package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.component.BigHeader

@Stable
private val FixBaseButtonRound: Dp = 0.dp

@Composable
fun FixBaseScreen(
    header: String,
    onPrevious: () -> Unit,
    btnText: String,
    onNext: () -> Unit,
    btnEnabled: Boolean,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .imePadding()
    ) {
        BigHeader(
            text = header,
            onPrevious = onPrevious
        )

        Column(modifier = Modifier.padding(horizontal = 40.dp)) {
            content()
        }

        BigRedRoundButton(
            text = btnText,
            onClick = onNext,
            enabled = btnEnabled,
            round = FixBaseButtonRound,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
        )
    }
}
