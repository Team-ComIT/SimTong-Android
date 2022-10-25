package com.comit.feature_auth.screen.signup

import android.app.Activity
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.feature_auth.vm.SignUpViewModel

private const val SIGN_UP_NAME = 0
private const val SIGN_UP_VERIFY = 1
private const val SIGN_UP_PASSWORD = 2
private const val SIGN_UP_NICKNAME = 3

@Composable
internal fun SignUpScreen(
    navController: NavController,
    viewModel: SignUpViewModel,
) {

    val signUpContainer = viewModel.container
    val signUpState = signUpContainer.stateFlow.collectAsState().value
    val signUpSideEffect = signUpContainer.sideEffectFlow

    Crossfade(targetState = signUpState.currentPage) { page ->
        when (page) {
            SIGN_UP_NAME -> {
                SignUpNameScreen(
                    toPrevious = { navController.popBackStack() },
                    toNext = { viewModel.navigatePage(SIGN_UP_VERIFY) },
                )
            }
            SIGN_UP_VERIFY -> {
                SignUpVerifyScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_NAME) },
                    toNext = { viewModel.navigatePage(SIGN_UP_PASSWORD) },
                )
            }
            SIGN_UP_PASSWORD -> {
                SignUpPasswordScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_VERIFY) },
                    toNext = { viewModel.navigatePage(SIGN_UP_NICKNAME) },
                )
            }
            SIGN_UP_NICKNAME -> {
                SignUpNicknameScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_PASSWORD) },
                    toNext = { },
                    onError = { }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUpScreen() {
    SignUpScreen(
        navController = rememberNavController(),
        viewModel = hiltViewModel(),
    )
}