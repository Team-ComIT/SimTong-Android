package com.comit.common.utils

const val SubStringYearStart = 0
const val SubStringYearEnd = 4

const val SubStringMonthStart = 5
const val SubStringMonthEnd = 7

const val SubStringDay = 8

const val IntSubStringYear = 0
const val IntSubStringMonth = 4
const val IntSubStringDay = 6

val string = String

fun dateToInt(date: String): Int {
    val year = date.substring(SubStringYearStart, SubStringYearEnd)
    val month = string.format("%02d", date.substring(SubStringMonthStart, SubStringMonthEnd).toInt())
    val day = string.format("%02d", date.substring(SubStringDay).toInt())

    return (year + month + day).toInt()
}

fun stringToDate(date: String): String {
    val year = date.substring(SubStringYearStart, SubStringYearEnd)
    val month = date.substring(SubStringMonthStart, SubStringMonthEnd)
    val day = date.substring(SubStringDay)

    return "$year-$month-$day"
}

fun intToDate(date: Int): String {
    val dateStr = date.toString()
    val year = dateStr.substring(IntSubStringYear, IntSubStringMonth)
    val month = dateStr.substring(IntSubStringMonth, IntSubStringDay)
    val day = dateStr.substring(IntSubStringDay)

    return "$year-$month-$day"
}
