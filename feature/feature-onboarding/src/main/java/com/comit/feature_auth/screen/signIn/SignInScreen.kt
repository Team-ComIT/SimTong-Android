package com.comit.feature_auth.screen.signIn

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.SignInSideEffect
import com.comit.feature_auth.vm.SignInViewModel
import com.comit.navigator.SimTongScreen
import kotlinx.coroutines.InternalCoroutinesApi

private val SignInScreenPadding = PaddingValues(
    start = 40.dp,
    top = 107.5.dp,
    end = 40.dp,
    bottom = 30.dp,
)

private val TextBtnPadding = PaddingValues(
    all = 3.dp,
)

private const val ErrMsgIdOrPasswordNotCorrect = "사원번호나 비밀번호가 일치하지 않습니다."
private const val ErrMsgNumberFormat = "올바른 사원 번호를 입력해주세요."

@OptIn(InternalCoroutinesApi::class)
@Composable
fun SignInScreen(
    navController: NavController,
    vm: SignInViewModel = hiltViewModel(),
) {
    val signInContainer = vm.container
    val signInState = signInContainer.stateFlow.collectAsState().value
    val signInSideEffect = signInContainer.sideEffectFlow

    signInSideEffect.observeWithLifecycle {
        when (it) {
            SignInSideEffect.NavigateToHomeScreen -> {
                navController.navigate(
                    route = SimTongScreen.Home.MAIN
                ) {
                    popUpTo(0)
                }
            }
            SignInSideEffect.IdOrPasswordNotCorrect -> {
                vm.inputErrMsgPassword(
                    msg = ErrMsgIdOrPasswordNotCorrect,
                )
            }
            SignInSideEffect.NumberFormat -> {
                vm.inputErrMsgEmployeeNumber(
                    msg = ErrMsgNumberFormat,
                )
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(SignInScreenPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 6.dp)
                    .height(26.dp),
                painter = painterResource(R.drawable.ic_sign_in_title),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier.size(43.dp),
                    painter = painterResource(
                        id = R.drawable.ic_sim,
                    ),
                    contentDescription = stringResource(id = R.string.description_ic_sim),
                )
                Image(
                    modifier = Modifier.size(43.dp),
                    painter = painterResource(
                        id = R.drawable.ic_dang,
                    ),
                    contentDescription = stringResource(id = R.string.description_ic_dang),
                )
                Image(
                    modifier = Modifier
                        .size(66.dp)
                        .offset(x = (-8).dp),
                    painter = painterResource(id = SimTongIcon.Logo.drawableId),
                    contentDescription = SimTongIcon.Logo.contentDescription,
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        SimTongTextField(
            value = signInState.employeeNumber,
            onValueChange = {
                vm.inputEmployeeNumber(it)
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.employee_number),
            keyboardType = KeyboardType.Number,
            error = signInState.errMsgEmployeeNumber,
        )

        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = signInState.password,
            onValueChange = {
                vm.inputPassword(it)
            },
            hintBackgroundColor = SimTongColor.Gray100,
            backgroundColor = SimTongColor.Gray50,
            hint = stringResource(id = R.string.password),
            isPassword = true,
            error = signInState.errMsgPassword,
        )

        Spacer(modifier = Modifier.height(30.dp))

        SimTongBigRoundButton(
            modifier = Modifier
                .fillMaxWidth(),
            text = stringResource(id = R.string.log_in),
            onClick = {
                vm.signIn(
                    employeeNumber = signInState.employeeNumber,
                    password = signInState.password,
                )
            },
            enabled = signInState.employeeNumber.isNotEmpty() && signInState.password.isNotEmpty(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Body8(
            modifier = Modifier
                .simClickable(
                    rippleEnabled = false,
                ) {
                    navController.navigate(
                        route = SimTongScreen.Auth.AUTH_FIND,
                    )
                }
                .padding(TextBtnPadding),
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
