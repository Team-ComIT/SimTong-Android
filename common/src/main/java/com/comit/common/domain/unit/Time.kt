package com.comit.common.domain.unit

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
val currentTime = SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()