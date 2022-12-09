package com.comit.feature_home.screen.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body3
import com.comit.navigator.SimTongScreen
import com.example.feature_home.R

@Stable
private val HorizontalPadding = PaddingValues(
    horizontal = 20.dp
)

@Composable
fun ShowScheduleScreen(
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        BigHeader(
            text = stringResource(id = R.string.schedule_comment),
            onPrevious = { navController.popBackStack() },
            modifier = Modifier
                .background(
                    color = SimTongColor.Background
                ),
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(HorizontalPadding)
        ) {
            Body3(text = stringResource(id = R.string.schedule_all))

            IconButton(
                onClick = {
                    navController.navigate(
                        route = SimTongScreen.Home.WRITE_SCHEDULE
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Add.drawableId),
                    contentDescription = SimTongIcon.Add.contentDescription,
                )
            }
        }
    }
}