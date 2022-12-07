package com.comit.core_design_system.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object MealsTime {
    const val launch = 900

    const val dinner = 1790
}

fun currentMealsTime(): Int =
    if (currentTime > MealsTime.launch) 2 else if (currentTime > MealsTime.dinner) 1 else 0

@SuppressLint("SimpleDateFormat")
private val currentTime =
    SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()
