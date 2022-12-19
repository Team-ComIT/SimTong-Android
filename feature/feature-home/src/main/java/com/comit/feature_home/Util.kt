package com.comit.feature_home

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.util.Log

const val SubStringYearStart = 0
const val SubStringYearEnd = 4

const val SubStringMonthStart = 5
const val SubStringMonthEnd = 7

const val SubStringDay = 8

val string = String

fun getStartAt(checkMonth: Int): String {
    Log.d("TAG", "checkMonth: $checkMonth")
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1

    val lastCalendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth - 1, 1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val year = lastCalendar.get(Calendar.YEAR).toString()
    val month = string.format("%02d", lastCalendar.get(Calendar.MONTH) + 1)
    val day = string.format("%02d", lastMax-min-1)

    return "$year-$month-$day"
}

fun getEndAt(checkMonth: Int): String {
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val lastDayCalendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth,
        calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    )

    val lastDay = lastDayCalendar.get(Calendar.DAY_OF_WEEK) - 1

    val year = lastDayCalendar.get(Calendar.YEAR).toString()
    val month = string.format("%02d", lastDayCalendar.get(Calendar.MONTH) + 1)
    val day = string.format("%02d", 7 - lastDay)

    return "$year-$month-$day"
}