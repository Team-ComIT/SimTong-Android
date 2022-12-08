package com.comit.feature_home.screen.schedule

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.comit.core_design_system.component.BigHeader
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

    BigHeader(
        text = headerText,
        onPrevious = { navController.popBackStack() },
    )

    
}