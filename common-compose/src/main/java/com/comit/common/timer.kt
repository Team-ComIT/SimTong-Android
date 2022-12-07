@file:Suppress(
    "MagicNumber",
)

package com.comit.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body10
import kotlinx.coroutines.delay

private const val MinuteForSecond: Int = 60

@Composable
fun SimCountDownTimer(
    totalTime: Int,
    onTimeChanged: (Int) -> Unit,
    onFinished: (() -> Unit)? = null,
    onClickRestart: (() -> Unit)? = null,
) {
    val minute = totalTime / MinuteForSecond
    val second =
        if (10 <= totalTime % MinuteForSecond) totalTime % MinuteForSecond
        else "0" + totalTime % MinuteForSecond

    LaunchedEffect(key1 = totalTime) {
        if (totalTime > 0) {
            delay(1000)
            onTimeChanged(totalTime - 1)
        } else {
            if (onFinished != null) {
                onFinished()
            }
        }
    }

    if (totalTime > 0) {
        Body10(
            text = "남은시간 $minute : $second",
            color = SimTongColor.MainColor,
        )
    } else {
        Body10(
            text = "재전송",
            color = SimTongColor.MainColor,
            onClick = {
                if (onClickRestart != null) {
                    onClickRestart()
                }
            },
        )
    }
}

@Preview
@Composable
fun PreviewSimTimer() {
    var totalTime by remember { mutableStateOf(5) }

    SimCountDownTimer(
        totalTime = totalTime,
        onClickRestart = {},
        onTimeChanged = { totalTime = it },
        onFinished = {}
    )
}
