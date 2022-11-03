package com.comit.common.domain.unit

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
val currentTime =
    SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()

private const val OneMinuteForSecond: Int = 60

private const val CheckDigit: Int = 10

fun getMinute(minute: Int): Int =
    minute / OneMinuteForSecond

fun getSecond(minute: Int): Int =
    if (CheckDigit <= minute % OneMinuteForSecond) minute % OneMinuteForSecond
    else minute % OneMinuteForSecond

fun getStringTime(minute: Int, second: Int): String {
    val str = StringBuilder()

    str
        .append("남은 시간")
        .append(" ")
        .append(minute)
        .append(" : ")
        .append(second)

    return str.toString()
}
