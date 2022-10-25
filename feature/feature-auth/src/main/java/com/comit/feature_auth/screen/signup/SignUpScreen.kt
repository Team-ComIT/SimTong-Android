package com.comit.feature_auth.screen.signup

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

@Suppress("UnusedPrivateMember")
@Composable
internal fun SignUpScreen(
    navController: NavController,
) {
    val viewModel: SignUpViewModel = hiltViewModel()

    val signUpContainer = viewModel.container
    val signUpState = signUpContainer.stateFlow.collectAsState().value
    val signUpSideEffect = signUpContainer.sideEffectFlow

    Crossfade(targetState = signUpState.currentPage) { page ->
        when (page) {
            SIGN_UP_NAME -> {
                SignUpNameScreen(
                    state = signUpState,
                    viewModel = viewModel,
                    toPrevious = { navController.popBackStack() },
                    toNext = { viewModel.navigatePage(SIGN_UP_VERIFY) },
                )
            }
            SIGN_UP_VERIFY -> {
                SignUpVerifyScreen(
                    state = signUpState,
                    viewModel = viewModel,
                    toPrevious = { viewModel.navigatePage(SIGN_UP_NAME) },
                    toNext = { viewModel.navigatePage(SIGN_UP_PASSWORD) },
                )
            }
            SIGN_UP_PASSWORD -> {
                SignUpPasswordScreen(
                    state = signUpState,
                    viewModel = viewModel,
                    toPrevious = { viewModel.navigatePage(SIGN_UP_VERIFY) },
                    toNext = { viewModel.navigatePage(SIGN_UP_NICKNAME) },
                )
            }
            SIGN_UP_NICKNAME -> {
                SignUpNicknameScreen(
                    state = signUpState,
                    viewModel = viewModel,
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
    )
}
