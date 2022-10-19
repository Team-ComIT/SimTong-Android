package com.comit.feature_auth.screen.find.password

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.typography.Body10
import com.comit.feature_auth.R

@Stable
private val CertificateNumberLength: Int = 6

@Composable
fun CheckCertificationNumber() {

    var certificationNumber by remember { mutableStateOf<String?>(null) }
    var certificationNumberError by remember { mutableStateOf<String?>(null) }

    val buttonEnabled = certificationNumber?.length == CertificateNumberLength

    val errorMsg = stringResource(id = R.string.certification_number_not_correct)

    Column(
        modifier = Modifier
            .padding(horizontal = 40.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Body10(
            text = "타이머",
            color = SimTongColor.MainColor,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )

        Spacer(modifier = Modifier.height(8.dp))

        SimTongTextField(
            value = certificationNumber ?: "",
            onValueChange = {
                certificationNumber = it
                certificationNumberError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.certification_number),
            error = certificationNumberError
        )

        BigRedRoundButton(
            text = stringResource(id = R.string.find_password),
            onClick = {
                certificationNumberError = errorMsg
            },
            enabled = buttonEnabled
        )
    }
}
