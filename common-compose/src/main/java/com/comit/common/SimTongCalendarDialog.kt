@file:Suppress("lexicographic")

package com.comit.common

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.comit.common.WeekOfDay.Friday
import com.comit.common.WeekOfDay.Monday
import com.comit.common.WeekOfDay.Saturday
import com.comit.common.WeekOfDay.Sunday
import com.comit.common.WeekOfDay.Thursday
import com.comit.common.WeekOfDay.Tuesday
import com.comit.common.WeekOfDay.Wednesday
import com.comit.common.utils.string
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body3
import java.time.LocalDate

@Stable
private val DialogSize: Dp = 330.dp

@Stable
private val DialogShape: Dp = 10.dp

@Stable
private val DialogItemPadding = PaddingValues(
    horizontal = 10.dp
)

@Stable
private val SimTongCalendarDateButtonSize: Dp = 20.dp

private const val Saturday: Int = 6

private const val Sunday: Int = 7

private const val Week: Int = 7

data class SimTongCalendarDialogData(
    val day: String,
    val month: Int,
    val year: Int,
    val weekend: Boolean,
    val thisMonth: Boolean,
    val startDay: Int,
    val endDay: Int,
)

@Composable
fun SimTongCalendarDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    startDay: Int,
    endDay: Int,
    isChangeStartDay: Boolean,
    onItemClicked: (String) -> Unit,
    properties: DialogProperties = DialogProperties(),
) {
    var checkMonth by remember { mutableStateOf(0) }

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DATE)
    )

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf((calendar.get(Calendar.MONTH) + 1)) }

    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Column(
                modifier = modifier
                    .size(DialogSize)
                    .background(
                        color = SimTongColor.White,
                        shape = RoundedCornerShape(DialogShape),
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 18.dp, end = 20.dp)
                        .fillMaxWidth(),
                ) {
                    val yearT = year.toString() + "년 "
                    val monthT = month.toString() + "월"
                    Body3(
                        text = yearT + monthT,
                        color = SimTongColor.Gray800,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .width(60.dp),
                    ) {
                        IconButton(
                            onClick = {
                                checkMonth --
                                val calendar = GregorianCalendar(
                                    today.get(Calendar.YEAR),
                                    today.get(Calendar.MONTH) + checkMonth,
                                    today.get(Calendar.DATE)
                                )
                                year = calendar.get(Calendar.YEAR)
                                month = (calendar.get(Calendar.MONTH) + 1)
                            },
                            modifier = Modifier.size(SimTongCalendarDateButtonSize),
                        ) {
                            Icon(
                                painter = painterResource(id = SimTongIcon.Calendar_Before.drawableId),
                                contentDescription = SimTongIcon.Calendar_Before.contentDescription,
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        IconButton(
                            onClick = {
                                checkMonth ++
                                val calendar = GregorianCalendar(
                                    today.get(Calendar.YEAR),
                                    today.get(Calendar.MONTH) + checkMonth,
                                    today.get(Calendar.DATE)
                                )
                                year = calendar.get(Calendar.YEAR)
                                month = (calendar.get(Calendar.MONTH) + 1)
                            },
                            modifier = Modifier.size(SimTongCalendarDateButtonSize)
                        ) {
                            Icon(
                                painter = painterResource(id = SimTongIcon.Calendar_After.drawableId),
                                contentDescription = SimTongIcon.Calendar_After.contentDescription
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                WeekTopRow()
                Spacer(modifier = Modifier.height(8.dp))

                SimTongCalendarDialogList(
                    list = getSimTongDialogList(checkMonth, startDay, endDay),
                    onItemClicked = onItemClicked,
                    onDismissRequest = onDismissRequest,
                    isChangeStartDay = isChangeStartDay,
                )
            }
        }
    }
}

@Composable
fun WeekTopRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(DialogItemPadding)
    ) {
        val list = listOf(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)

        items(list) {
            val textColor =
                if (it == Sunday || it == Saturday) SimTongColor.Gray300 else SimTongColor.Gray800

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth(1.toFloat() / 7.toFloat())
            ) {
                Body14(
                    text = it,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun SimTongCalendarDialogList(
    list: List<SimTongCalendarDialogData>,
    onItemClicked: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isChangeStartDay: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(DialogItemPadding),
    ) {
        repeat(list.size / Week) { size ->
            val rowList = list.subList(size * Week, size * Week + Week)

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(modifier = Modifier.fillMaxWidth()) {
                items(rowList) {
                    SimTongCalendarDialogItem(
                        day = it.day.toInt(),
                        month = it.month,
                        year = it.year,
                        startDay = it.startDay,
                        endDay = it.endDay,
                        weekend = it.weekend,
                        thisMonth = it.thisMonth,
                        onItemClicked = onItemClicked,
                        onDismissRequest = onDismissRequest,
                        isChangeStartDay = isChangeStartDay,
                        modifier = Modifier
                            .fillParentMaxWidth(1.toFloat() / 7.toFloat()),
                    )
                }
            }
        }
    }
}

@Composable
fun SimTongCalendarDialogItem(
    modifier: Modifier = Modifier,
    day: Int,
    month: Int,
    year: Int,
    startDay: Int,
    endDay: Int,
    weekend: Boolean,
    thisMonth: Boolean,
    onItemClicked: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isChangeStartDay: Boolean,
) {
    val itemDate = (year.toString() + string.format("%02d", month) + string.format("%02d", day)).toInt()

    val textColor =
        if (!thisMonth) SimTongColor.Gray100
        else if (startDay > itemDate && !isChangeStartDay) SimTongColor.Gray100
        else if (endDay < itemDate && isChangeStartDay) SimTongColor.Gray100
        else if (weekend) SimTongColor.Gray300
        else SimTongColor.Gray800

    val clickable = textColor != SimTongColor.Gray100

    Box(
        modifier = modifier
            .height(30.dp)
            .simClickable(
                rippleEnabled = false,
            ) {
                if (clickable) {
                    onItemClicked(itemDate.toString())
                    onDismissRequest()
                }
            },
    ) {
        Body14(
            text = day.toString(),
            color = textColor,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

private fun getSimTongDialogList(
    checkMonth: Int,
    startDay: Int,
    endDay: Int,
): List<SimTongCalendarDialogData> {

    val calendarList: ArrayList<SimTongCalendarDialogData> = ArrayList()

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH) + checkMonth, 1
    )
    val min = calendar.get(Calendar.DAY_OF_WEEK) - 1
    val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH) + 1

    val lastCalendar = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH) + checkMonth - 1, 1
    )
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    for (i in min - 1 downTo 0) {
        calendarList.add(
            SimTongCalendarDialogData(
                day = (lastMax - i).toString(),
                month = month,
                year = year,
                weekend = false,
                thisMonth = false,
                startDay = startDay,
                endDay = endDay,
            )
        )
    }

    for (i in 1..max) {
        val dayOfWeek = LocalDate.of(year, month, i).dayOfWeek.value
        val weekend = dayOfWeek == com.comit.common.Saturday || dayOfWeek == com.comit.common.Sunday

        calendarList.add(
            SimTongCalendarDialogData(
                day = i.toString(),
                month = month,
                year = year,
                weekend = weekend,
                thisMonth = true,
                startDay = startDay,
                endDay = endDay,
            )
        )
    }
    for (i in 1..Week) {
        if (calendarList.size % Week == 0)
            break
        else {
            calendarList.add(
                SimTongCalendarDialogData(
                    day = i.toString(),
                    month = month,
                    year = year,
                    weekend = false,
                    thisMonth = false,
                    startDay = startDay,
                    endDay = endDay,
                )
            )
        }
    }

    return calendarList.toList()
}

private object WeekOfDay {
    const val Sunday = "일"
    const val Monday = "월"
    const val Tuesday = "화"
    const val Wednesday = "수"
    const val Thursday = "목"
    const val Friday = "금"
    const val Saturday = "토"
}
