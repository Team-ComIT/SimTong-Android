package com.comit.feature_home.calendar

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.util.Log
import com.comit.feature_home.mvi.FetchHolidayState
import com.comit.feature_home.mvi.FetchScheduleState
import java.time.LocalDate
import kotlin.collections.ArrayList

private const val Saturday: Int = 6

private const val Sunday: Int = 7

private const val Week: Int = 7

private const val MonthStart: Int = 1

private const val MonthEnd: Int = 31

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

    for (i in MonthStart..MonthEnd) {
        restDayList.add(false)
        annualDayList.add(false)
        workDayList.add(0)
    }

    for (element in holidayList) {
        if (element.type == TypeName.HOLIDAY) {
            restDayList[element.date[9].toString().toInt() - 1] = true
        }
        if (element.type == TypeName.ANNUAL) {
            annualDayList[element.date[9].toString().toInt() - 1] = true
        }
    }

    if(workCountList != null) {
        for(i in workCountList.indices) {
            val start = workCountList[i].start_At.substring(8).toInt()
            val end = workCountList[i].end_At.substring(8).toInt()

            if(start == end) {
                workDayList[start - 1] = workDayList[start - 1] + 1
            } else {
                for(j in start..end) {
                    workDayList[j - 1]  = workDayList[j-1] + 1
                }
            }
        }
    }

    for (i in min - 1 downTo 0) {
        calendarList.add(
            SimTongCalendarData(
                day = (lastMax - i).toString(),
                workCount = 0,
                weekend = false,
                thisMouth = false,
                restDay = false,
                annualDay = false,
                today = false
            )
        )
    }

    for (i in 1..max) {

        val dayOfWeek = LocalDate.of(year, month, i).dayOfWeek.value
        val weekend = dayOfWeek == Saturday || dayOfWeek == Sunday
        val todayCheck = today.get(Calendar.DATE) == i && checkMonth == 0

        calendarList.add(
            SimTongCalendarData(
                day = i.toString(),
                workCount = workDayList[i - 1],
                weekend = weekend,
                thisMouth = true,
                restDay = restDayList[i - 1],
                annualDay = annualDayList[i - 1],
                today = todayCheck
            )
        )
    }

    for (i in 1..Week) {
        if (calendarList.size % Week == 0) {
            break
        } else {
            calendarList.add(
                SimTongCalendarData(
                    day = i.toString(),
                    workCount = 0,
                    weekend = false,
                    thisMouth = false,
                    restDay = false,
                    annualDay = false,
                    today = false
                )
            )
        }
    }

    return calendarList
}

object TypeName {
    const val HOLIDAY = "HOLIDAY"
    const val ANNUAL = "ANNUAL"
}
