package com.comit.feature_home.screen.schedule

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.example.feature_home.R

@Composable
fun WriteSchedule(
    navController: NavController,
    isNew: Boolean,
    title: String = "",
    scheduleStart: String = "",
    scheduleEnd: String = "",
    alarm: String = ""
) {
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
                value = "",
                hint = stringResource(id = R.string.title_hint),
                onValueChange = { },
                title = stringResource(id = R.string.title)
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = "",
                hint = stringResource(id = R.string.date_start_hint),
                onValueChange = { },
                title = stringResource(id = R.string.date)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SimTongTextField(
                value = "",
                hint = stringResource(id = R.string.date_finish_hint),
                onValueChange = { },
            )

            Spacer(modifier = Modifier.height(8.dp))

            SimTongTextField(
                value = "",
                hint = stringResource(id = R.string.alarm_hint),
                onValueChange = { },
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
    WriteSchedule(
        navController = rememberNavController(),
        isNew = true
    )
}