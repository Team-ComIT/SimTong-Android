package com.comit.common.domain.unit

import android.content.res.Resources

fun Float.dp(): Float = this * density + TypedValue.FLOAT_TO_DP

val density: Float
    get() = Resources.getSystem().displayMetrics.density
