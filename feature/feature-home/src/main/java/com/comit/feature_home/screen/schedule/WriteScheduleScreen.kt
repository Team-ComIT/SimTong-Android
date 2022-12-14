@file:OptIn(InternalCoroutinesApi::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED", "TooGenericExceptionCaught", "SwallowedException")

package com.comit.feature_home.screen.schedule

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.SimTongBtnField
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_home.mvi.WriteScheduleSideInEffect
import com.comit.feature_home.string
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi
import java.sql.Date
import java.sql.Time
import java.util.UUID

private const val AlarmErrMessage = "모든 항목에 형식이 일치하게 작성되었는지 확인해주세요."

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

    if (!isNew) {
        LaunchedEffect(vm) {
            vm.inputTitle(msg = title)
            vm.inputScheduleStart(msg = scheduleStart)
            vm.inputScheduleEnd(msg = scheduleEnd)
        }
    }

    writeScheduleSideInEffect.observeWithLifecycle() {
        when (it) {
            WriteScheduleSideInEffect.WriteScheduleSuccess -> {
                navController.popBackStack()
            }
            WriteScheduleSideInEffect.WriteScheduleFail -> {
                vm.inputErrMsgTitle("")
                vm.inputErrMsgScheduleStart("")
                vm.inputErrMsgScheduleEnd("")
                vm.inputErrMsgAlarm("모든 항목에 형식이 일치하게 작성되었는지 확인해주세요.")
            }
        }
    }

    val headerText =
        if (isNew) stringResource(id = R.string.schedule_make)
        else stringResource(id = R.string.schedule_fix)

    val alarmHint =
        if (isNew) stringResource(id = R.string.alarm_hint)
        else stringResource(id = R.string.alarm_hint_should)

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
                onValueChange = { vm.inputTitle(msg = it) },
                title = stringResource(id = R.string.title),
                error = writeScheduleState.errMsgTitle,
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongBtnField(
                onClick = {  },
                value = writeScheduleState.scheduleStart,
                hint = stringResource(id = R.string.date_start_hint),
                title = stringResource(id = R.string.date),
                error = writeScheduleState.errMsgScheduleStart,
            )

            Spacer(modifier = Modifier.height(10.dp))

            SimTongBtnField(
                onClick = {  },
                value = writeScheduleState.scheduleEnd,
                hint = stringResource(id = R.string.date_finish_hint),
                error = writeScheduleState.errMsgScheduleEnd
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongBtnField(
                onClick = {  },
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
                                scheduleStart = Date.valueOf(writeScheduleState.scheduleStart),
                                scheduleEnd = Date.valueOf(writeScheduleState.scheduleEnd),
                                alarm = Time.valueOf(writeScheduleState.alarm).toString()
                            )
                        } else {
                            vm.changeSchedule(
                                scheduleId = UUID.fromString(scheduleId),
                                title = writeScheduleState.title,
                                startAt = Date.valueOf(writeScheduleState.scheduleStart),
                                endAt = Date.valueOf(writeScheduleState.scheduleEnd),
                                alarm = Time.valueOf(writeScheduleState.alarm).toString()
                            )
                        }
                    } else {
                        if (isNew) {
                            vm.writeSchedule(
                                title = writeScheduleState.title,
                                scheduleStart = Date.valueOf(writeScheduleState.scheduleStart)!!,
                                scheduleEnd = Date.valueOf(writeScheduleState.scheduleEnd)!!,
                                alarm = null
                            )
                        } else {
                            vm.changeSchedule(
                                scheduleId = UUID.fromString(scheduleId),
                                title = writeScheduleState.title,
                                startAt = Date.valueOf(writeScheduleState.scheduleStart),
                                endAt = Date.valueOf(writeScheduleState.scheduleEnd),
                                alarm = null
                            )
                        }
                    }
                } catch (e: Exception) {
                    vm.inputErrMsgTitle("")
                    vm.inputErrMsgScheduleStart("")
                    vm.inputErrMsgScheduleEnd("")
                    vm.inputErrMsgAlarm(AlarmErrMessage)
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
