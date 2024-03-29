@file:Suppress("ComplexMethod", "NestedBlockDepth", "LongMethod")

package com.comit.feature_home.calendar

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import com.comit.common.utils.dateToInt
import com.comit.common.utils.string
import com.comit.feature_home.SubStringDay
import com.comit.feature_home.getStartAt
import com.comit.feature_home.mvi.FetchHolidayState
import com.comit.feature_home.mvi.FetchScheduleState
import java.time.LocalDate
import kotlin.collections.ArrayList

private const val Saturday: Int = 6

private const val Sunday: Int = 7

private const val Week: Int = 7

private const val CalendarStart: Int = 1

private const val CalendarEnd: Int = 48

fun organizeList(
    checkMonth: Int,
    holidayList: List<FetchHolidayState.Holiday>,
    workCountList: List<FetchScheduleState.Schedule>?,
): ArrayList<SimTongCalendarData> {

    val calendarList = ArrayList<SimTongCalendarData>()
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1
    val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1

    val lastCalendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth - 1, 1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    val restDayList = ArrayList<Boolean>()
    val annualDayList = ArrayList<Boolean>()
    val workDayList = ArrayList<Int>()

    for (i in CalendarStart..CalendarEnd) {
        restDayList.add(false)
        annualDayList.add(false)
        workDayList.add(0)
    }

    for (element in holidayList) {
        if (element.type == TypeName.HOLIDAY) {
            if (dateToInt(element.date) < (year.toString() + string.format("%02d", month) + "00").toInt()) {
                val day = element.date.substring(SubStringDay).toInt()
                restDayList[day - getStartAt(checkMonth).substring(SubStringDay).toInt()] = true
            } else if (dateToInt(element.date) > (year.toString() + string.format("%02d", month) + "31").toInt()) {
                val day = element.date.substring(SubStringDay).toInt()
                restDayList[day + min + max - 1] = true
            } else {
                restDayList[element.date.substring(SubStringDay).toInt() - 1 + min] = true
            }
        }
        if (element.type == TypeName.ANNUAL) {
            if (dateToInt(element.date) < (year.toString() + string.format("%02d", month) + "00").toInt()) {
                val day = element.date.substring(SubStringDay).toInt()
                annualDayList[day - getStartAt(checkMonth).substring(SubStringDay).toInt()] = true
            } else if (dateToInt(element.date) > (year.toString() + string.format("%02d", month) + "31").toInt()) {
                val day = element.date.substring(SubStringDay).toInt()
                annualDayList[day + min + max - 1] = true
            } else {
                annualDayList[element.date.substring(SubStringDay).toInt() - 1 + min] = true
            }
        }
    }

    if (workCountList != null) {
        for (i in workCountList.indices) {
            val start = workCountList[i].startAt.substring(SubStringDay).toInt()
            val end = workCountList[i].endAt.substring(SubStringDay).toInt()

            if (start == end) {
                workDayList[start - 1 + min] = workDayList[start - 1 + min] + 1
            } else {
                for (j in start..end) {
                    workDayList[j - 1 + min] = workDayList[j - 1 + min] + 1
                }
            }
        }
    }

    var j = 0

    for (i in min - 1 downTo 0) {
        calendarList.add(
            SimTongCalendarData(
                day = (lastMax - i).toString(),
                workCount = workDayList[j],
                weekend = false,
                thisMouth = false,
                restDay = restDayList[j],
                annualDay = annualDayList[j],
                today = false
            )
        )
        j++
    }

    for (i in 1..max) {

        val dayOfWeek = LocalDate.of(year, month, i).dayOfWeek.value
        val weekend = dayOfWeek == Saturday || dayOfWeek == Sunday
        val todayCheck = today.get(Calendar.DATE) == i && checkMonth == 0

        calendarList.add(
            SimTongCalendarData(
                day = i.toString(),
                workCount = workDayList[j],
                weekend = weekend,
                thisMouth = true,
                restDay = restDayList[j],
                annualDay = annualDayList[j],
                today = todayCheck
            )
        )
        j++
    }

    for (i in 1..Week) {
        if (calendarList.size % Week == 0) {
            break
        } else {
            calendarList.add(
                SimTongCalendarData(
                    day = i.toString(),
                    workCount = workDayList[j],
                    weekend = false,
                    thisMouth = false,
                    restDay = restDayList[j],
                    annualDay = annualDayList[j],
                    today = false
                )
            )
        }
        j++
    }

    return calendarList
}

object TypeName {
    const val HOLIDAY = "HOLIDAY"
    const val ANNUAL = "ANNUAL"
}
