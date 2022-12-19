package com.comit.common.utils

const val SubStringYearStart = 0
const val SubStringYearEnd = 4

const val SubStringMonthStart = 5
const val SubStringMonthEnd = 7

const val SubStringDay = 8

val string = String

fun dateToInt(date: String): Int {
    val year = string.format("%02d", date.substring(SubStringYearStart, SubStringYearEnd))
    val month = string.format("%02d", date.substring(SubStringMonthStart, SubStringMonthEnd))
    val day = date.substring(SubStringDay)

    return (year + month + day).toInt()
}

fun stringToDate(date: String): String {
    val year = date.substring(SubStringYearStart, SubStringYearEnd)
    val month = date.substring(SubStringMonthStart, SubStringMonthEnd)
    val day = date.substring(SubStringDay)

    return "$year-$month-$day"
}
