package com.comit.common.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

@Composable
fun SimTimer(
    totalTime: Int,
) {
    var totalTime by remember {
        mutableStateOf(totalTime)
    }

    val minute = totalTime / 60
    val second =
        if (10 <= totalTime % 60) totalTime % 60
        else "0" + totalTime % 60

    LaunchedEffect(key1 = totalTime) {
        if (totalTime > 0) {
            delay(1000)
            totalTime -= 1
        }
    }

//    Body10(
//        text = "남은시간 $minute : $second",
//        color = SimTongColor.MainColor,
//    )
}

@Preview
@Composable
fun PreviewSimTimer() {
    SimTimer(totalTime = 100)
}
