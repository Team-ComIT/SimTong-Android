package com.comit.feature_auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body8
import com.comit.feature_auth.R

@Stable
val SignInTopRowHeight = 43.dp

@Composable
fun SignInScreen(

){
    var id by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }
    var idError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(id == null || id == "" || password == null || password == "")

    Column(
        modifier = Modifier
            .padding(40.dp, 107.5.dp, 40.dp, 30.dp)
    ) {

        SignInTopLogo()

        Spacer(modifier = Modifier.height(40.dp))
        
        SimTongTextField(
            value = id ?: "",
            onValueChange = {
                id = it
                idError = null
                passwordError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.employee_number),
            error = idError
        )

        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = password ?: "",
            onValueChange = {
                password = it
                idError = null
                passwordError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.password),
            isPassword = true,
            error = passwordError
        )
        
        Spacer(modifier = Modifier.height(30.dp))

        BigRedRoundButton(
            text = "Log in",
            onClick = {
                idError = ""
                passwordError = "아이디나 비밀번호가 일치하지 않습니다."
            },
            enabled = buttonEnabled,
            modifier = Modifier
                .fillMaxWidth()
        )

        SignInBottomBtn()

    }
}

@Composable
fun SignInTopLogo(

){
    Body1(
        text = stringResource(id = R.string.mind_share),
        color = SimTongColor.OtherColor.Gray7E,
    )

    Row(
        modifier = Modifier
            .height(SignInTopRowHeight)
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.ic_sim),
            contentDescription = stringResource(id = R.string.description_ic_sim)
        )

        Image(
            painter = painterResource(
                id = R.drawable.ic_dang
            ),
            contentDescription = stringResource(id = R.string.description_ic_dang)
        )

        Image(
            painter = painterResource(
                id = SimTongIcons.Others.Logo
            ),
            contentDescription = stringResource(id = R.string.description_ic_logo),
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Stable
val SignInBottomBtnTextPadding = 5.dp

@Composable
fun SignInBottomBtn(

){
    Row(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.BottomCenter)
    ) {
        Body8(
            text = stringResource(id = R.string.sign_up),
            color = SimTongColor.OtherColor.GrayB3,
            modifier = Modifier
                .noRippleClickable { }
                .padding(SignInBottomBtnTextPadding)
        )

        Body8(
            text = stringResource(id = R.string.contour),
            color = SimTongColor.OtherColor.GrayB3,
            modifier = Modifier
                .padding(SignInBottomBtnTextPadding)
        )

        Body8(
            text = stringResource(id = R.string.find_employee_number_password),
            color = SimTongColor.OtherColor.GrayB3,
            modifier = Modifier
                .noRippleClickable { }
                .padding(SignInBottomBtnTextPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen()
}