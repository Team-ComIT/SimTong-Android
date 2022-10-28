package com.comit.common.domain.unit

import android.content.res.Resources

fun Float.dp(): Float = this * density + FLOAT_TO_DP

internal val density: Float
    get() = Resources.getSystem().displayMetrics.density

private const val FLOAT_TO_DP = 0.5f