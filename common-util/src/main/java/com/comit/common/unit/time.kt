package com.comit.common.unit

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date

@SuppressLint("SimpleDateFormat")
val currentTime =
    SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()

fun String.toLocalDateTime(): LocalDateTime =
    LocalDateTime.parse(this, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
