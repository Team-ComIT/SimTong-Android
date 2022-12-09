@file:OptIn(ExperimentalMaterialApi::class, ExperimentalMaterialApi::class)

package com.comit.feature_home.screen.closeday

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.modifier.noRippleClickable
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body6
import com.comit.feature_home.calendar.SimTongCalendarDate
import com.comit.feature_home.calendar.SimTongCalendarList
import com.comit.feature_home.calendar.WeekTopRow
import com.comit.feature_home.calendar.getWorkCountList
import com.comit.feature_home.calendar.organizeList
import com.comit.feature_home.screen.GetHolidayViewModel
import com.example.feature_home.R
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.Calendar
import java.util.GregorianCalendar

@Stable
private val SimTongCalendarTotalRound = RoundedCornerShape(20.dp)

@Composable
fun WriteClosedDayScreen(
    navController: NavController,
    getHolidayViewModel: GetHolidayViewModel = hiltViewModel(),
) {

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    val today = GregorianCalendar()
    val thisYear = today.get(Calendar.YEAR)
    val thisMonth = (today.get(Calendar.MONTH) + 1)
    val thisDay = today.get(Calendar.DATE)

    var workState by remember { mutableStateOf("") }
    var workStateText by remember { mutableStateOf("") }

    var yearT by remember { mutableStateOf(thisYear) }
    var monthT by remember { mutableStateOf(thisMonth) }
    var dayT by remember { mutableStateOf(thisDay) }

    var checkMonth by remember { mutableStateOf(0) }
    // var restDayList by remember { mutableStateOf(getRestDayList(getHolidayViewModel, getHolidayState, thisYear, thisMonth)) }
    var workCountList by remember { mutableStateOf(getWorkCountList(thisYear, thisMonth)) }
    // var calendarList by remember { mutableStateOf(organizeList(0, restDayList, annualDayList, workCountList)) }

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            val saveEnabled = workState != workStateText
            val saveColor =
                if (saveEnabled) SimTongColor.MainColor400 else SimTongColor.MainColor100

            Column() {

                val workClose = stringResource(id = R.string.work_close)
                val workAnnual = stringResource(id = R.string.work_annual)
                val workWork = stringResource(id = R.string.work_work)

                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 30.dp, top = 17.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    val _yearT = yearT.toString() + stringResource(id = R.string.calendar_year) + " "
                    val _monthT = monthT.toString() + stringResource(id = R.string.calendar_month) + " "
                    val _dayT = dayT.toString() + stringResource(id = R.string.calendar_day) + "은 "

                    Body6(
                        text = "$_yearT$_monthT$_dayT\"$workStateText\"입니다.",
                        color = SimTongColor.Gray800
                    )

                    Body5(
                        text = stringResource(id = R.string.save),
                        color = saveColor,
                        onClick = {
                            if (saveEnabled) {
                                coroutineScope.launch {
                                    bottomSheetState.hide()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .width(240.dp)
                ) {
                    WriteCloseDayItem(
                        text = workClose,
                        color = SimTongColor.MainColor400,
                        onItemClicked = {
                            workStateText = it
                        }
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    WriteCloseDayItem(
                        text = workAnnual,
                        color = SimTongColor.FocusBlue,
                        onItemClicked = {
                            workStateText = it
                        }
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    WriteCloseDayItem(
                        text = workWork,
                        color = SimTongColor.Gray200,
                        onItemClicked = {
                            workStateText = it
                        }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    ) {
        var checkMonth by remember { mutableStateOf(0) }

        val today = GregorianCalendar()
        val calendar = GregorianCalendar(
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DATE)
        )

        var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
        var month by remember { mutableStateOf(calendar.get(Calendar.MONTH) + 1) }

        var workCountList by remember { mutableStateOf(getWorkCountList(year, month)) }

        var calendarList by remember {
            mutableStateOf(
                organizeList(
                    0,
                    listOf(),
                    workCountList
                )
            )
        }

        LaunchedEffect(getHolidayViewModel) {
            getHolidayViewModel.getHolidayList(Date.valueOf(String.format("%02d", year) + "-" + String.format("%02d", month) + "-01"))
        }

        val lifecycle = LocalLifecycleOwner.current
        LaunchedEffect(getHolidayViewModel) {
            getHolidayViewModel.holidayList.observe(lifecycle) {
                calendarList = organizeList(checkMonth, it, workCountList)
            }
        }

        Column(
            modifier = Modifier
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
                    checkMonth--
                    calendar.add(Calendar.MONTH, checkMonth)
                    month = calendar.get(Calendar.MONTH) + 1
                    year = calendar.get(Calendar.YEAR)
                    getHolidayViewModel.getHolidayList(Date.valueOf(String.format("%02d", year) + "-" + String.format("%02d", month) + "-01"))
                    workCountList = getWorkCountList(year, month)
                },
                onNextClicked = {
                    checkMonth++
                    calendar.add(Calendar.MONTH, checkMonth)
                    month = calendar.get(Calendar.MONTH) + 1
                    year = calendar.get(Calendar.YEAR)
                    getHolidayViewModel.getHolidayList(Date.valueOf(String.format("%02d", year) + "-" + String.format("%02d", month) + "-01"))
                    workCountList = getWorkCountList(year, month)
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
}

@Composable
fun WriteCloseDayItem(
    text: String,
    color: Color,
    onItemClicked: (String) -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .noRippleClickable { onItemClicked(text) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Body1(
                text = text[0].toString(),
                color = SimTongColor.White,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Body6(text = text)
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun ShowWriteClosedDayScreen() {
    // WriteClosedDayScreen(navController = rememberNavController())
}
