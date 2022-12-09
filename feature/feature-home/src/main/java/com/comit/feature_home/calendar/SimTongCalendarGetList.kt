package com.comit.feature_home.calendar

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.util.Log
import androidx.compose.material.BottomDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.comit.feature_home.mvi.FetchHolidayState
import com.comit.feature_home.screen.GetHolidayViewModel
import java.sql.Date
import java.time.LocalDate
import kotlin.collections.ArrayList

private const val Saturday: Int = 6

private const val Sunday: Int = 7

private const val Week: Int = 7

fun organizeList(
    checkMonth: Int,
    restDayList: List<Boolean>,
    annualDayList: List<Boolean>,
    workCountList: List<Int>,
): ArrayList<SimTongCalendarData> {
    val calendarList = ArrayList<SimTongCalendarData>()
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth, 1)
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1
    val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)
    val mouth = calendar.get(Calendar.MONTH) + 1

    val lastCalendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + checkMonth - 1, 1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

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

        val dayOfWeek = LocalDate.of(year, mouth, i).dayOfWeek.value
        val weekend = dayOfWeek == Saturday || dayOfWeek == Sunday
        val todayCheck = today.get(Calendar.DATE) == i && checkMonth == 0

        calendarList.add(
            SimTongCalendarData(
                day = i.toString(),
                workCount = workCountList[i - 1],
                weekend = weekend,
                thisMouth = true,
                restDay = restDayList[i - 1],
                annualDay = annualDayList[i - 1],
                today = todayCheck
            )
        )
        // Log.d("TAG", "organizeList: "+calendarList[i].restDay)
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

private const val MonthStart: Int = 1

private const val MonthEnd: Int = 31

fun getRestDayList(
    vm: GetHolidayViewModel,
    state: FetchHolidayState,
    year: Int,
    month: Int
): ArrayList<Boolean> {
    val restDayList = ArrayList<Boolean>()

    val year = String.format("%02d", year)
    val month = String.format("%02d", month)

    try {
        vm.getHolidayList(Date.valueOf("$year-$month-01"))
    } catch (_: Exception) { }

    for (i in 0..30) {
        restDayList.add(false)
    }

    for(i in 0 until state.holidayList.size) {
        val listItem = state.holidayList[i]
        if(listItem.type == TypeName.HOLIDAY) {
            restDayList[listItem.date[9].toString().toInt() - 1] = true
        }
    }

    return restDayList
}

fun getAnnualDayList(
    year: Int,
    month: Int
): ArrayList<Boolean> {
    val annualDayList = ArrayList<Boolean>()

    for (i in MonthStart..MonthEnd) {
        if (year + month == 0) {
            annualDayList.add(false)
        } else {
            annualDayList.add(false)
        }
    }

    return annualDayList
}

fun getWorkCountList(
    year: Int,
    month: Int
): ArrayList<Int> {
    val workCountList = ArrayList<Int>()

    for (i in MonthStart..MonthEnd) {
        if (year + month == 0) {
            workCountList.add(i)
        } else {
            workCountList.add(0)
        }
    }

    return workCountList
}

object TypeName {
    const val HOLIDAY = "HOLIDAY"
}
