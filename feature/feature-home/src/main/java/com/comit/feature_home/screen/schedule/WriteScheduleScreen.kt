@file:OptIn(InternalCoroutinesApi::class)

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
import com.comit.feature_home.mvi.WriteScheduleState
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi

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
                },
                title = stringResource(id = R.string.title)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = writeScheduleState.scheduleStart,
                hint = stringResource(id = R.string.date_start_hint),
                onValueChange = {
                    vm.inputScheduleStart(msg = it)
                },
                title = stringResource(id = R.string.date)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SimTongTextField(
                value = writeScheduleState.scheduleEnd,
                hint = stringResource(id = R.string.date_finish_hint),
                onValueChange = {
                    vm.inputScheduleEnd(msg = it)
                },
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = writeScheduleState.alarm,
                hint = stringResource(id = R.string.alarm_hint),
                onValueChange = {
                    vm.inputAlarm(msg = it)
                },
                title = stringResource(id = R.string.alarm)
            )
        }
        SimTongBigRoundButton(
            text = stringResource(id = R.string.check),
            enabled = btnEnabled,
            onClick = {
                navController.popBackStack()
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