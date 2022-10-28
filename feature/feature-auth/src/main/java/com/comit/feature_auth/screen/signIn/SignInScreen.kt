package com.comit.feature_auth.screen.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import com.comit.navigator.SimTongScreen

private val SignInTopRowHeight = 43.dp

@Stable
private val SignInScreenPadding = PaddingValues(
    start = 40.dp, top = 107.5.dp, end = 40.dp, bottom = 30.dp,
)

@Stable
private val TextBtnPadding = PaddingValues(
    all = 3.dp,
)

@Composable
fun SignInScreen(
    navController: NavController,
) {
    var id by remember { mutableStateOf<String?>(null) }
    var password by remember { mutableStateOf<String?>(null) }
    var idError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    val buttonEnabled = !(id.isNullOrEmpty() || password.isNullOrEmpty())

    val errorMsg = stringResource(
        id = R.string.error_message,
    )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SignInScreenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            SignInTopLayout()

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
                error = idError,
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
                error = passwordError,
            )

            Spacer(modifier = Modifier.height(30.dp))

            SimTongBigRoundButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(id = R.string.log_in),
                onClick = {
                    idError = ""
                    passwordError = errorMsg

                    // TODO ("test 딴에서는 바로 홈으로 이동")
                    navController.navigate(
                        route = SimTongScreen.Home.MAIN,
                    )
                },
                enabled = buttonEnabled,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Body8(
                modifier = Modifier
                    .noRippleClickable {
                        navController.navigate(
                            route = SimTongScreen.Auth.AUTH_FIND,
                        )
                    }
                    .padding(TextBtnPadding)
                ,
                text = stringResource(id = R.string.find_employee_number_password),
                color = SimTongColor.OtherColor.GrayB3,
            )

            Spacer(modifier = Modifier.weight(1f))

            UnderlineBody9(
                modifier = Modifier
                    .padding(TextBtnPadding),
                text = stringResource(
                    id = R.string.sign_up_induction_msg,
                ),
                underlineText = listOf(
                    stringResource(
                        id = R.string.sign_up,
                    ),
                ),
                color = SimTongColor.Gray300,
                onClick = {
                    navController.navigate(
                        route = SimTongScreen.Auth.SIGN_UP,
                    )
                },
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
}

@Composable
private fun SignInTopLayout() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Body1(
            text = stringResource(id = R.string.mind_share),
            color = SimTongColor.OtherColor.Gray7E,
        )

        Row(
            modifier = Modifier
                .height(SignInTopRowHeight),
        ) {
            Image(
                painter = painterResource(
                    id = R.drawable.ic_sim,
                ),
                contentDescription = stringResource(id = R.string.description_ic_sim),
            )

            Image(
                painter = painterResource(
                    id = R.drawable.ic_dang,
                ),
                contentDescription = stringResource(id = R.string.description_ic_dang),
            )

            Image(
                painter = painterResource(id = SimTongIcon.Logo.drawableId),
                contentDescription = SimTongIcon.Logo.contentDescription,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSignInScreen() {
    SignInScreen(
        navController = rememberNavController()
    )
}
