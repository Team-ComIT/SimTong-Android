@file:OptIn(
    ExperimentalMaterialApi::class,
    InternalCoroutinesApi::class,
)
@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.comit.feature_home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body6
import com.comit.feature_home.calendar.SimTongCalendar
import com.comit.feature_home.calendar.SimTongCalendarStatus
import com.comit.feature_home.mvi.CloseDaySideEffect
import com.comit.feature_home.util.HomeMessage
import com.comit.feature_home.util.SubStringMonthEnd
import com.comit.feature_home.util.SubStringMonthStart
import com.comit.feature_home.util.SubStringYearEnd
import com.comit.feature_home.util.SubStringYearStart
import com.comit.feature_home.util.toDateFormat
import com.comit.feature_home.vm.CloseDayViewModel
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.sql.Date
import java.util.Calendar
import java.util.GregorianCalendar

private val CalendarPadding = PaddingValues(
    horizontal = 20.dp
)

@Composable
fun WriteClosedDayScreen(
    navController: NavController,
    closeDayViewModel: CloseDayViewModel = hiltViewModel(),
) {
    val toast = rememberToast()

    val closeDayContainer = closeDayViewModel.container
    val closeDayState = closeDayContainer.stateFlow.collectAsState().value
    val closeDaySideEffect = closeDayContainer.sideEffectFlow

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    if (closeDayState.year.isEmpty()) {
        val today = GregorianCalendar()
        closeDayViewModel.inputYearState(today.get(Calendar.YEAR).toString())
        closeDayViewModel.inputMonthState((today.get(Calendar.MONTH) + 1).toDateFormat())
    }

    var workState by remember { mutableStateOf("") }
    var workStateText by remember { mutableStateOf("") }

    var refresh by remember { mutableStateOf(false) }

    closeDaySideEffect.observeWithLifecycle {
        when (it) {
            CloseDaySideEffect.CloseDayChangeSuccess -> {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
                refresh = true
            }

            CloseDaySideEffect.DateInputWrong -> {
                toast(HomeMessage.Holiday.DateBadRequest)
            }

            CloseDaySideEffect.AlreadyHoliday -> {
                toast(HomeMessage.Holiday.AlreadyHoliday)
            }

            CloseDaySideEffect.TooManyHoliday -> {
                toast(HomeMessage.Holiday.TooManyHoliday)
            }

            CloseDaySideEffect.AlreadyAnnualDay -> {
                toast(HomeMessage.Holiday.AnnualAlready)
            }

            CloseDaySideEffect.TooManyAnnualDay -> {
                toast(HomeMessage.Holiday.TooManyAnnual)
            }

            CloseDaySideEffect.AlreadyWork -> {
                toast(HomeMessage.Holiday.AlreadyWork)
            }

            CloseDaySideEffect.CannotChangeWorkState -> {
                toast(HomeMessage.Holiday.CannotChangeWork)
            }
        }
    }

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            val saveEnabled = workState != workStateText
            val saveColor =
                if (saveEnabled) SimTongColor.MainColor400 else SimTongColor.MainColor100

            Column {

                val workClose = stringResource(id = R.string.work_close)
                val workAnnual = stringResource(id = R.string.work_annual)
                val workWork = stringResource(id = R.string.work_work)

                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 30.dp, top = 17.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    val yearT =
                        closeDayState.year + stringResource(id = R.string.calendar_year) + " "
                    val monthT =
                        closeDayState.month + stringResource(id = R.string.calendar_month) + " "
                    val dayT = closeDayState.day + stringResource(id = R.string.calendar_day) + "은 "

                    if (closeDayState.year.isNotEmpty()) {
                        closeDayViewModel.checkLeftHoliday(year = closeDayState.year.toInt())
                    }

                    Body6(
                        text = "$yearT$monthT$dayT\"$workStateText\"입니다.",
                        color = SimTongColor.Gray800
                    )

                    Body5(
                        text = stringResource(id = R.string.save),
                        color = saveColor,
                        onClick = {
                            val date =
                                Date.valueOf("${closeDayState.year}-${closeDayState.month}-${closeDayState.day}")
                            refresh = false

                            if (saveEnabled) {
                                when (workStateText) {
                                    workClose -> {
                                        closeDayViewModel.setHoliday(date.toString())
                                    }

                                    workAnnual -> {
                                        closeDayViewModel.setAnnualDay(date)
                                    }

                                    else -> {
                                        closeDayViewModel.setWorkDay(date)
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    )
                }

                Spacer(modifier = Modifier.height(9.dp))

                Row(modifier = Modifier.padding(start = 20.dp)) {
                    Body6(text = "남은 연차: ")

                    Body6(
                        text = closeDayState.leftHoliday.toString() + "개",
                        color = SimTongColor.FocusBlue,
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

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
        Column() {
            Spacer(modifier = Modifier.height(22.5.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 26.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = SimTongIcon.Back_Big.drawableId),
                        contentDescription = SimTongIcon.Back_Big.contentDescription
                    )
                }
                Body3(
                    text = stringResource(id = R.string.schedule_day_write),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(end = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(44.dp))

            SimTongCalendar(
                onNextClicked = { date, _ ->
                    closeDayViewModel.inputMonthState(
                        date.toString().substring(SubStringMonthStart, SubStringMonthEnd)
                    )
                    closeDayViewModel.inputYearState(
                        date.toString().substring(SubStringYearStart, SubStringYearEnd)
                    )
                },
                onBeforeClicked = { date, _ ->
                    closeDayViewModel.inputMonthState(
                        date.toString().substring(SubStringMonthStart, SubStringMonthEnd)
                    )
                    closeDayViewModel.inputYearState(
                        date.toString().substring(SubStringYearStart, SubStringYearEnd)
                    )
                },
                onItemClicked = { day, _workState ->
                    workState = _workState
                    workStateText = _workState
                    closeDayViewModel.inputDayState(day.toDateFormat())
                    coroutineScope.launch {
                        bottomSheetState.show()
                    }
                },
                statusName = SimTongCalendarStatus.WRITTEN,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CalendarPadding),
                refresh = refresh
            )
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
            .simClickable(
                rippleEnabled = false,
            ) { onItemClicked(text) }
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
    WriteClosedDayScreen(navController = rememberNavController())
}
