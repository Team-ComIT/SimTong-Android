package com.comit.core_design_system.util

import android.annotation.SuppressLint
import androidx.compose.runtime.Stable
import java.text.SimpleDateFormat
import java.util.Date

@Stable
private val TimeLunch: Int = 900

@Stable
private val TimeDinner: Int = 1700

@SuppressLint("SimpleDateFormat")
fun time(): Int {
    val time = SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()

    var timeCheck = 0

    if (time > TimeDinner) timeCheck = 2

    else if (time > TimeLunch) { timeCheck = 1 }

    return timeCheck
}
