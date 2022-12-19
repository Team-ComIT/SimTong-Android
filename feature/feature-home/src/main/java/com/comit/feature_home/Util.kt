package com.comit.feature_home

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar

const val SubStringYearStart = 0
const val SubStringYearEnd = 4

const val SubStringMonthStart = 5
const val SubStringMonthEnd = 7

const val SubStringDay = 8

val string = String

private const val Week = 7

fun getStartAt(checkMonth: Int): String {
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1

    val lastCalendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth - 1, 1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    lastCalendar.add(Calendar.DATE, lastMax - min)

    val year = lastCalendar.get(Calendar.YEAR).toString()
    val month = string.format("%02d", lastCalendar.get(Calendar.MONTH) + 1)
    val day = string.format("%02d", lastCalendar.get(Calendar.DATE))

    return "$year-$month-$day"
}

fun getEndAt(checkMonth: Int): String {
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val lastDayCalendar = GregorianCalendar(
        today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth,
        calendar.getActualMaximum(Calendar.DATE)
    )

    val lastDay = lastDayCalendar.get(Calendar.DAY_OF_WEEK)

    return if (lastDay == Week) {
        val year = lastDayCalendar.get(Calendar.YEAR).toString()
        val month = string.format("%02d", lastDayCalendar.get(Calendar.MONTH) + 1)
        val day = string.format("%02d", lastDayCalendar.get(Calendar.DATE))

        "$year-$month-$day"
    } else {
        val year = lastDayCalendar.get(Calendar.YEAR).toString()
        val month = string.format("%02d", lastDayCalendar.get(Calendar.MONTH) + 2)
        val day = string.format("%02d", Week - lastDay)

        "$year-$month-$day"
    }
}
