@file:OptIn(InternalCoroutinesApi::class)

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_home.mvi.WriteScheduleSideInEffect
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi
import java.sql.Date
import java.sql.Time
import java.text.ParseException
import java.text.SimpleDateFormat

@Composable
fun WriteScheduleScreen(
    navController: NavController,
    isNew: Boolean = false,
    title: String = "",
    scheduleStart: String = "",
    scheduleEnd: String = "",
    alarm: String = "",
    vm: WriteScheduleViewModel = hiltViewModel(),
) {
    val writeScheduleContainer = vm.container
    val writeScheduleState = writeScheduleContainer.stateFlow.collectAsState().value
    val writeScheduleSideInEffect = writeScheduleContainer.sideEffectFlow

    LaunchedEffect(vm) {
        vm.inputTitle(msg = title)
        vm.inputScheduleStart(msg = scheduleStart)
        vm.inputScheduleEnd(msg = scheduleEnd)
        vm.inputAlarm(msg = alarm)
    }

    writeScheduleSideInEffect.observeWithLifecycle() {
        when (it) {
            WriteScheduleSideInEffect.WriteScheduleSuccess -> {
                navController.popBackStack()
            }
        }
    }

    val headerText =
        if (isNew) stringResource(id = R.string.schedule_make)
        else stringResource(id = R.string.schedule_write)

    val btnEnabled = true

    Column {
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
                    vm.inputErrMsgTitle(null)
                    vm.inputErrMsgScheduleStart(null)
                    vm.inputErrMsgScheduleEnd(null)
                    vm.inputErrMsgAlarm(null)
                },
                title = stringResource(id = R.string.title),
                error = writeScheduleState.errMsgTitle
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = writeScheduleState.scheduleStart,
                hint = stringResource(id = R.string.date_start_hint),
                onValueChange = {
                    vm.inputScheduleStart(msg = it)
                    vm.inputErrMsgTitle(null)
                    vm.inputErrMsgScheduleStart(null)
                    vm.inputErrMsgScheduleEnd(null)
                    vm.inputErrMsgAlarm(null)
                },
                title = stringResource(id = R.string.date),
                error = writeScheduleState.errMsgScheduleStart
            )

            Spacer(modifier = Modifier.height(10.dp))

            SimTongTextField(
                value = writeScheduleState.scheduleEnd,
                hint = stringResource(id = R.string.date_finish_hint),
                onValueChange = {
                    vm.inputScheduleEnd(msg = it)
                    vm.inputErrMsgTitle(null)
                    vm.inputErrMsgScheduleStart(null)
                    vm.inputErrMsgScheduleEnd(null)
                    vm.inputErrMsgAlarm(null)
                },
                error = writeScheduleState.errMsgTitle
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = writeScheduleState.alarm,
                hint = stringResource(id = R.string.alarm_hint),
                onValueChange = {
                    vm.inputAlarm(msg = it)
                    vm.inputErrMsgTitle(null)
                    vm.inputErrMsgScheduleStart(null)
                    vm.inputErrMsgScheduleEnd(null)
                    vm.inputErrMsgAlarm(null)
                },
                title = stringResource(id = R.string.alarm),
                error = writeScheduleState.errMsgAlarm
            )
        }
        SimTongBigRoundButton(
            text = stringResource(id = R.string.check),
            enabled = btnEnabled,
            onClick = {
                try {
                    val dateFormatter = SimpleDateFormat("yyyy-MM-DD")

                    Log.d("TAG", "WriteScheduleScreen: ")
                    Log.d("TAG", "WriteScheduleScreen: " + Date.valueOf("2022-12-09"))
                    if (writeScheduleState.alarm.isNotEmpty()) {
                        vm.writeSchedule(
                            title = writeScheduleState.title,
                            scheduleStart = dateFormatter.parse(writeScheduleState.scheduleStart)!!,
                            scheduleEnd = Date.valueOf(writeScheduleState.scheduleEnd),
                            alarm = Time.valueOf(writeScheduleState.alarm)!!
                        )
                    } else {
                        vm.writeSchedule(
                            title = writeScheduleState.title,
                            scheduleStart = dateFormatter.parse(writeScheduleState.scheduleStart)!!,
                            scheduleEnd = dateFormatter.parse(writeScheduleState.scheduleEnd)!!,
                            alarm = null
                        )
                    }
//                    vm.writeSchedule(
//                        title = title,
//                        scheduleStart = dateFormatter.parse(scheduleStart)!!,
//                        scheduleEnd = dateFormatter.parse(scheduleEnd)!!,
//                        alarm = timeFormatter.parse(alarm) as Time,
//                    )
                    Log.d("TAG", "scheduleStart: " + dateFormatter.format(dateFormatter.parse(writeScheduleState.scheduleStart)!!))
                    Log.d("TAG", "scheduleEnd: " + dateFormatter.format(dateFormatter.parse(writeScheduleState.scheduleEnd)!!))
                    navController.popBackStack()
                } catch (e: ParseException) {
                    vm.inputErrMsgTitle("")
                    vm.inputErrMsgScheduleStart("")
                    vm.inputErrMsgScheduleEnd("")
                    vm.inputErrMsgAlarm("모든 항목에 형식이 일치하게 작성되었는지 확인해주세요.")
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
    WriteScheduleScreen(
        navController = rememberNavController(),
        isNew = true
    )
}