package com.comit.feature_auth.screen.error

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body7
import com.comit.core_design_system.typography.Title1
import com.comit.feature_auth.R

@Composable
fun ErrorScreen(
    navController: NavController,
    errorMessage: String,
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        Spacer(modifier = Modifier.height(100.dp))

        Title1(
            text = stringResource(id = R.string.error_opps),
            color = SimTongColor.MainColor,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Body1(
            text = stringResource(id = R.string.error_unknown_message),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Body5(
            text = stringResource(id = R.string.error_info_title),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Body7(
            text = errorMessage,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Body12(
            text = stringResource(id = R.string.error_report_message),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.weight(1f))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.error_go_back),
        ) {
            navController.popBackStack()
        }
    }
}
