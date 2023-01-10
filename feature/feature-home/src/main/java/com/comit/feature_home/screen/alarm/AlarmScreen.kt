package com.comit.feature_home.screen.alarm

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body8
import com.comit.feature_home.util.HomeMessage
import com.example.feature_home.R

@Composable
fun AlarmScreen(
    navController: NavController,
) {
    Column {
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
                text = stringResource(id = R.string.alarm),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
                    .padding(end = 24.dp)
            )
        }

        Body8(
            text = HomeMessage.Alarm.AlarmNotFound,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}
