@file:OptIn(InternalCoroutinesApi::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED", "TooGenericExceptionCaught", "SwallowedException")

package com.comit.feature_home.screen.schedule

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.systemBarPaddings
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_home.mvi.WriteScheduleSideInEffect
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi
import java.sql.Time
import java.util.UUID

private const val InputTextFormErrorMessage = "모든 항목에 형식이 일치하게 작성되었는지 확인해주세요."
private const val TokenExceptionMessage = "토큰 만료. 다시 로그인해주세요"
private const val CannotFindScheduleMessage = "변경할 일정을 확인할 수 없습니다"

@Composable
fun WriteScheduleScreen(
    navController: NavController,
    isNew: Boolean = false,
    scheduleId: String = "",
    title: String = "",
    scheduleStart: String = "",
    scheduleEnd: String = "",
    vm: WriteScheduleViewModel = hiltViewModel(),
) {
    val writeScheduleContainer = vm.container
    val writeScheduleState = writeScheduleContainer.stateFlow.collectAsState().value
    val writeScheduleSideInEffect = writeScheduleContainer.sideEffectFlow

//    var startYear by remember { mutableStateOf("0") }
//    var startMonth by remember { mutableStateOf("0") }
//    var startDay by remember { mutableStateOf("0") }
//    var endYear by remember { mutableStateOf("0") }
//    var endMonth by remember { mutableStateOf("0") }
//    var endDay by remember { mutableStateOf("0") }
    var isChangeStartDay by remember { mutableStateOf(true) }

    if (!isNew) {
        LaunchedEffect(vm) {
            vm.inputTitle(msg = title)
            vm.inputScheduleStart(msg = scheduleStart)
            vm.inputScheduleEnd(msg = scheduleEnd)

            Log.d("TAG", "title: "+writeScheduleState.scheduleStart)
            Log.d("TAG", "WriteScheduleScreen: "+writeScheduleState.scheduleEnd)

//            startYear = scheduleStart.substring(SubStringYearStart, SubStringYearEnd)
//            startMonth = scheduleStart.substring(SubStringMonthStart, SubStringMonthEnd)
//            startDay = scheduleStart.substring(SubStringDay)
//
//            endYear = scheduleEnd.substring(SubStringYearStart, SubStringYearEnd)
//            endMonth = scheduleEnd.substring(SubStringMonthStart, SubStringMonthEnd)
//            endDay = scheduleEnd.substring(SubStringDay)
        }
    }

    writeScheduleSideInEffect.observeWithLifecycle() {
        when (it) {
            WriteScheduleSideInEffect.WriteScheduleSuccess -> {
                navController.popBackStack()
            }
            WriteScheduleSideInEffect.InputTextFormError -> {
                vm.inputErrMsgAll(msg = InputTextFormErrorMessage)
            }
            WriteScheduleSideInEffect.TokenException -> {
                vm.inputErrMsgAll(msg = TokenExceptionMessage)
            }
            WriteScheduleSideInEffect.CannotFindSchedule -> {
                vm.inputErrMsgAll(msg = CannotFindScheduleMessage)
            }
        }
    }

    var calendarDialogVisible by remember { mutableStateOf(false) }

    val headerText =
        if (isNew) stringResource(id = R.string.schedule_make)
        else stringResource(id = R.string.schedule_fix)

    val alarmHint =
        if (isNew) stringResource(id = R.string.alarm_hint)
        else stringResource(id = R.string.alarm_hint_should)

    Column(
        modifier = Modifier.padding(
            bottom = systemBarPaddings.calculateBottomPadding(),
        )
    ) {
        BigHeader(
            text = headerText,
            onPrevious = { navController.popBackStack() },
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            SimTongTextField(
                value = writeScheduleState.title,
                hint = stringResource(id = R.string.title_hint),
                onValueChange = {
                    vm.inputTitle(msg = it)
                    vm.cancelErrMsgAll()
                },
                title = stringResource(id = R.string.title),
                error = writeScheduleState.errMsgTitle,
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongBtnField(
                onClick = {
                    calendarDialogVisible = true
                    isChangeStartDay = true
                },
                value = writeScheduleState.scheduleStart,
                hint = stringResource(id = R.string.date_start_hint),
                title = stringResource(id = R.string.date),
                error = writeScheduleState.errMsgScheduleStart,
            )

            Spacer(modifier = Modifier.height(10.dp))

            SimTongBtnField(
                onClick = {
                    calendarDialogVisible = true
                    isChangeStartDay = false
                },
                value = writeScheduleState.scheduleEnd,
                hint = stringResource(id = R.string.date_finish_hint),
                error = writeScheduleState.errMsgScheduleEnd
            )

            SimTongCalendarDialog(
                visible = calendarDialogVisible,
                onDismissRequest = {
                    calendarDialogVisible = false
                    vm.cancelErrMsgAll()
                },
                onItemClicked = {
                    if (isChangeStartDay) {
                        vm.inputScheduleStart(stringToDate(it))
                    } else {
                        vm.inputScheduleEnd(stringToDate(it))
                    }
                    vm.cancelErrMsgAll()
                },
                startDay = dateToInt(writeScheduleState.scheduleStart),
                endDay = dateToInt(writeScheduleState.scheduleEnd),
                isChangeStartDay = isChangeStartDay
            )
//            try {
//
//                )
//            } catch (e: ) {
//                Log.d("TAG", "WriteScheduleScreen: "+writeScheduleState.scheduleStart)
//                Log.d("TAG", "WriteScheduleScreen: "+writeScheduleState.scheduleEnd)
//            }

            Spacer(modifier = Modifier.height(8.dp))

            SimTongBtnField(
                onClick = { },
                value = writeScheduleState.alarm,
                hint = alarmHint,
                title = stringResource(id = R.string.alarm),
                error = writeScheduleState.errMsgAlarm,
            )
        }

        val btnEnabled = writeScheduleState.title.isNotEmpty() &&
                writeScheduleState.scheduleStart.isNotEmpty() &&
                writeScheduleState.scheduleEnd.isNotEmpty()

        SimTongBigRoundButton(
            text = stringResource(id = R.string.check),
            enabled = btnEnabled,
            onClick = {
                try {
                    if (writeScheduleState.alarm.isNotEmpty()) {
                        if (isNew) {
                            vm.writeSchedule(
                                title = writeScheduleState.title,
                                scheduleStart = writeScheduleState.scheduleStart,
                                scheduleEnd = writeScheduleState.scheduleEnd,
                                alarm = Time.valueOf(writeScheduleState.alarm).toString()
                            )
                        } else {
                            vm.changeSchedule(
                                scheduleId = UUID.fromString(scheduleId),
                                title = writeScheduleState.title,
                                startAt = writeScheduleState.scheduleStart,
                                endAt = writeScheduleState.scheduleEnd,
                                alarm = Time.valueOf(writeScheduleState.alarm).toString()
                            )
                        }
                    } else {
                        if (isNew) {
                            vm.writeSchedule(
                                title = writeScheduleState.title,
                                scheduleStart = writeScheduleState.scheduleStart,
                                scheduleEnd = writeScheduleState.scheduleEnd,
                                alarm = null
                            )
                        } else {
                            vm.changeSchedule(
                                scheduleId = UUID.fromString(scheduleId),
                                title = writeScheduleState.title,
                                startAt = writeScheduleState.scheduleStart,
                                endAt = writeScheduleState.scheduleEnd,
                                alarm = null
                            )
                        }
                    }
                } catch (e: Exception) {
                    vm.inputErrMsgAll(InputTextFormErrorMessage)
                }
            },
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .imePadding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowWriteSchedule() {
//    WriteScheduleScreen(
//        navController = rememberNavController(),
//        isNew = true
//    )
}
