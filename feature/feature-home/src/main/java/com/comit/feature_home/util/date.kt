package com.comit.feature_home.util

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar

const val SubStringYearStart = 0
const val SubStringYearEnd = 4

const val SubStringMonthStart = 5
const val SubStringMonthEnd = 7

const val SubStringDay = 8

internal fun Int.toDateFormat() =
    String.format("%02d", this)

private const val Week = 7

internal fun getStartAt(checkMonth: Int): String {
    val today = GregorianCalendar()
    val calendar =
        GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1

    val lastCalendar =
        GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth - 1, 1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    lastCalendar.add(Calendar.DATE, lastMax - min)

    val year = lastCalendar.get(Calendar.YEAR).toString()
    val month = (lastCalendar.get(Calendar.MONTH) + 1).toDateFormat()
    val day = (lastCalendar.get(Calendar.DATE)).toDateFormat()

    return "$year-$month-$day"
}

internal fun getEndAt(checkMonth: Int): String {
    val today = GregorianCalendar()
    val calendar =
        GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val lastDayCalendar = GregorianCalendar(
        today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth,
        calendar.getActualMaximum(Calendar.DATE)
    )

    val lastDay = lastDayCalendar.get(Calendar.DAY_OF_WEEK)

    return if (lastDay == Week) {
        val year = lastDayCalendar.get(Calendar.YEAR).toString()
        val month = (lastDayCalendar.get(Calendar.MONTH) + 1).toDateFormat()
        val day = lastDayCalendar.get(Calendar.DATE).toDateFormat()

        "$year-$month-$day"
    } else {
        val year = lastDayCalendar.get(Calendar.YEAR).toString()
        val month = (lastDayCalendar.get(Calendar.MONTH) + 2).toDateFormat()
        val day = (Week - lastDay).toDateFormat()

        "$year-$month-$day"
    }
}
