package com.comit.core_design_system.util

import com.comit.common.domain.unit.currentTime

object MealsTime {
    const val launch = 900

    const val dinner = 1790
}

fun currentMealsTime(): Int =
    if (currentTime > MealsTime.launch) 2 else if (currentTime > MealsTime.dinner) 1 else 0
