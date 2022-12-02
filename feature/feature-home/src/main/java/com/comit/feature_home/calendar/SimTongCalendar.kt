package com.comit.feature_home.calendar

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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.noRippleClickable
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.UnderlineBody12
import com.example.feature_home.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.GregorianCalendar

data class SimTongCalendarData(
    val day: String,
    val workCount: Int,
    val weekend: Boolean,
    val thisMouth: Boolean,
    val restDay: Boolean,
    val annualDay: Boolean,
    val today: Boolean
)

private const val Week: Int = 7

@Stable
private val SimTongCalendarTotalHeight: Dp = 420.dp

@Stable
private val SimTongCalendarTotalRound = RoundedCornerShape(20.dp)

@Stable
private val SimTongCalendarTotalHorizontalPadding = PaddingValues(
    horizontal = 20.dp
)

@ExperimentalMaterialApi
@Composable
fun SimTongCalendar(
    modifier: Modifier = Modifier,
) {
    var checkMonth by remember { mutableStateOf(0) }

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE))

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH) + 1) }

    var restDayList by remember { mutableStateOf(getRestDayList(year, month)) }
    var annualDayList by remember { mutableStateOf(getAnnualDayList(year, month)) }
    var workCountList by remember { mutableStateOf(getWorkCountList(year, month)) }
    var calendarList by remember { mutableStateOf(organizeList(0, restDayList, annualDayList, workCountList)) }

    Column(
        modifier = modifier
            .background(
                color = SimTongColor.Gray50,
                shape = SimTongCalendarTotalRound
            )
    ) {
        Spacer(modifier = Modifier.height(15.dp))

        SimTongCalendarDate(
            year = year.toString(),
            month = month.toString(),
            onBeforeClicked = {
                checkMonth --
                calendar.add(Calendar.MONTH, checkMonth)
                month = calendar.get(Calendar.MONTH) + 1
                year = calendar.get(Calendar.YEAR)
                restDayList = getRestDayList(year, month)
                annualDayList = getAnnualDayList(year, month)
                workCountList = getWorkCountList(year, month)
                calendarList = organizeList(checkMonth, restDayList, annualDayList, workCountList)
            },
            onNextClicked = {
                checkMonth ++
                calendar.add(Calendar.MONTH, checkMonth)
                month = calendar.get(Calendar.MONTH) + 1
                year = calendar.get(Calendar.YEAR)
                restDayList = getRestDayList(year, month)
                annualDayList = getAnnualDayList(year, month)
                workCountList = getWorkCountList(year, month)
                calendarList = organizeList(checkMonth, restDayList, annualDayList, workCountList)
            }
        )

        Spacer(modifier = Modifier.height(37.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
        ) {
            WeekTopRow()

            SimTongCalendarList(list = calendarList)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun SimTongCalendar(
    coroutineScope: CoroutineScope,
    bottomSheetValue: ModalBottomSheetState,
    restDayList: ArrayList<Boolean>,
    annualDayList: ArrayList<Boolean>,
    calendarList: ArrayList<SimTongCalendarData>,
    onDateClicked: (String, String, String, String) -> Unit,
    onMonthChanged: (Int, Int) -> Unit,
) {
    var checkMonth by remember { mutableStateOf(0) }

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DATE))

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH) + 1) }

    var restDayList by remember { mutableStateOf(restDayList) }
    var annualDayList by remember { mutableStateOf(annualDayList) }
    var workCountList by remember { mutableStateOf(getWorkCountList(year, month)) }
    var calendarList by remember { mutableStateOf(calendarList) }

    Column(
        modifier = Modifier
            .padding(SimTongCalendarTotalHorizontalPadding)
            .fillMaxWidth()
            .height(SimTongCalendarTotalHeight)
            .background(
                color = SimTongColor.Gray50,
                shape = SimTongCalendarTotalRound
            )
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        SimTongCalendarDate(
            year = year.toString(),
            month = month.toString(),
            onBeforeClicked = {
                checkMonth --
                calendar.add(Calendar.MONTH, checkMonth)
                month = calendar.get(Calendar.MONTH) + 1
                year = calendar.get(Calendar.YEAR)
                onMonthChanged(year, month)
                restDayList = getRestDayList(year, month)
                annualDayList = getAnnualDayList(year, month)
                workCountList = getWorkCountList(year, month)
                calendarList = organizeList(checkMonth, restDayList, annualDayList, workCountList)
            },
            onNextClicked = {
                checkMonth ++
                calendar.add(Calendar.MONTH, checkMonth)
                month = calendar.get(Calendar.MONTH) + 1
                year = calendar.get(Calendar.YEAR)
                onMonthChanged(year, month)
                restDayList = getRestDayList(year, month)
                annualDayList = getAnnualDayList(year, month)
                workCountList = getWorkCountList(year, month)
                calendarList = organizeList(checkMonth, restDayList, annualDayList, workCountList)
            }
        )

        Spacer(modifier = Modifier.height(37.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
        ) {
            WeekTopRow()

            SimTongCalendarList(
                list = calendarList,
                onItemClicked = { day, workState ->
                    onDateClicked(year.toString(), month.toString(), day.toString(), workState)
                    coroutineScope.launch {
                        bottomSheetValue.show()
                    }
                }
            )
        }
    }
}

@Stable
private val SimTongCalendarDateButtonSize: Dp = 20.dp

@Composable
fun SimTongCalendarDate(
    year: String,
    month: String,
    onBeforeClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val yearT = year + stringResource(id = R.string.calendar_year)
    val monthT = month + stringResource(id = R.string.calendar_month)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 16.dp, top = 18.dp, end = 20.dp)
            .fillMaxWidth()
    ) {
        Body3(
            text = "$yearT $monthT",
            color = SimTongColor.Gray800
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .width(60.dp)
        ) {
            IconButton(
                onClick = { onBeforeClicked() },
                modifier = Modifier
                    .size(SimTongCalendarDateButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Calendar_Before.drawableId),
                    contentDescription = SimTongIcon.Calendar_Before.contentDescription
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            IconButton(
                onClick = { onNextClicked() },
                modifier = Modifier
                    .size(SimTongCalendarDateButtonSize)
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Calendar_After.drawableId),
                    contentDescription = SimTongIcon.Calendar_After.contentDescription
                )
            }
        }
    }
}

@Composable
fun WeekTopRow() {

    val sunday: String = stringResource(id = R.string.calendar_sunday)
    val monday: String = stringResource(id = R.string.calendar_monday)
    val tuesday: String = stringResource(id = R.string.calendar_tuesday)
    val wednesday: String = stringResource(id = R.string.calendar_wednesday)
    val thursday: String = stringResource(id = R.string.calendar_thursday)
    val friday: String = stringResource(id = R.string.calendar_friday)
    val saturday: String = stringResource(id = R.string.calendar_saturday)

    LazyRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        val list = listOf(sunday, monday, tuesday, wednesday, thursday, friday, saturday)

        items(list) {
            val textColor =
                if (it == stringResource(id = R.string.calendar_sunday) ||
                    it == stringResource(id = R.string.calendar_saturday)
                ) SimTongColor.Gray300
                else SimTongColor.Gray800
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth(1.toFloat() / 7.toFloat())
            ) {
                Body6(
                    text = it,
                    color = textColor
                )
            }
        }
    }
}

@Composable
fun SimTongCalendarList(
    list: List<SimTongCalendarData>,
    onItemClicked: (Int, String) -> Unit = { day, workState -> },
    onCalendarClicked: () -> Unit = {}
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        repeat(list.size / Week) { size ->
            val rowList = list.subList(size * Week, size * Week + Week)

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(rowList) {
                    SimTongCalendarItem(
                        day = it.day,
                        workCount = it.workCount,
                        weekend = it.weekend,
                        thisMouth = it.thisMouth,
                        restDay = it.restDay,
                        annualDay = it.annualDay,
                        today = it.today,
                        onItemClicked = onItemClicked,
                        onCalendarClicked = onCalendarClicked,
                        modifier = Modifier
                            .fillParentMaxWidth(1.toFloat() / 7.toFloat())
                    )
                }
            }
        }
    }
}

@Stable
private val SimTongCalendarItemBoxSize: Dp = 23.64.dp

@Stable
private val SimTongCalendarItemBoxRound = RoundedCornerShape(9.09.dp)

@Composable
fun SimTongCalendarItem(
    modifier: Modifier = Modifier,
    day: String,
    workCount: Int,
    weekend: Boolean,
    thisMouth: Boolean,
    restDay: Boolean,
    annualDay: Boolean,
    today: Boolean,
    onItemClicked: (Int, String) -> Unit,
    onCalendarClicked: () -> Unit
) {
    val textColor =
        if (weekend) SimTongColor.Gray300
        else if (thisMouth) SimTongColor.Gray800
        else SimTongColor.Gray100

    val backgroundColor =
        if (restDay) SimTongColor.MainColor400
        else if (annualDay) SimTongColor.FocusBlue
        else SimTongColor.Gray200

    val workState =
        if (restDay) stringResource(id = R.string.work_close)
        else if (annualDay) stringResource(id = R.string.work_annual)
        else stringResource(id = R.string.work_work)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleClickable {
            if (thisMouth && !weekend) {
                onItemClicked(day.toInt(), workState)
            }
            onCalendarClicked()
        }
    ) {
        if (today) {
            UnderlineBody12(
                text = day,
                underlineText = listOf(day),
                underlineTextColor = textColor
            )
        } else {
            Body12(
                text = day,
                color = textColor
            )
        }

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(SimTongCalendarItemBoxSize)
                .background(
                    color = backgroundColor,
                    shape = SimTongCalendarItemBoxRound
                )
        ) {
            if (restDay) {
                Body13(
                    text = stringResource(id = R.string.calendar_rest_day),
                    color = SimTongColor.White
                )
            } else if (annualDay) {
                Body13(
                    text = stringResource(id = R.string.calendar_annual_day),
                    color = SimTongColor.White
                )
            } else if (thisMouth && !weekend) {
                if (workCount != 0) {
                    Body11(
                        text = workCount.toString(),
                        color = SimTongColor.Gray400
                    )
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ShowCalendar() {
    Column(modifier = Modifier.fillMaxSize()) {
        SimTongCalendar()
    }
}