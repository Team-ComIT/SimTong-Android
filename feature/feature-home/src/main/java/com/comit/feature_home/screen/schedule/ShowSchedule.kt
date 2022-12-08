package com.comit.feature_home.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.example.feature_home.R

@Composable
fun ShowSchedule(
    navController: NavController
) {
    BigHeader(
        text = stringResource(id = R.string.schedule_comment),
        onPrevious = { navController.popBackStack() },
        modifier = Modifier
            .background(
                color = SimTongColor.Background
            ),
    )

}