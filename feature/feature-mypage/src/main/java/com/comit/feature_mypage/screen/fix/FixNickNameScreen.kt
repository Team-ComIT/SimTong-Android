package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R

@Composable
internal fun FixNickNameScreen(
    navController: NavController,
) {
    var nickName by remember { mutableStateOf("") }
    var nickNameError by remember { mutableStateOf<String?>(null) }

    FixBaseScreen(
        header = stringResource(id = R.string.nick_name_input),
        onPrevious = { },
        btnText = stringResource(id = R.string.nick_name_fix),
        onNext = { nickNameError = "" },
        btnEnabled = nickName.isNotEmpty(),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = nickName,
            onValueChange = { nickName = it },
            title = stringResource(id = R.string.nick_name_input),
            error = nickNameError,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixNickNameScreen() {
    FixNickNameScreen(
        navController = rememberNavController(),
    )
}
