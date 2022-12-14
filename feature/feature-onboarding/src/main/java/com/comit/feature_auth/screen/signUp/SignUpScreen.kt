package com.comit.feature_auth.screen.signUp

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.feature_auth.mvi.SignUpSideEffect
import com.comit.feature_auth.vm.ImageLimitSizeInKB
import com.comit.feature_auth.vm.SignUpViewModel
import com.comit.navigator.SimTongScreen
import kotlinx.coroutines.InternalCoroutinesApi

private const val SIGN_UP_NAME = 0
private const val SIGN_UP_VERIFY = 1
private const val SIGN_UP_PASSWORD = 2
private const val SIGN_UP_NICKNAME = 3

private const val UserInfoNotMatchingMessage = "이름과 사번 정보가 일치하지 않습니다."
private const val EmailAlreadyVerifyMessage = "이미 인증된 이메일입니다."
private const val EmailVerifyCountOverMessage = "이메일 인증 횟수를 초과했습니다."
private const val EmailVerifyCodeNotCorrectMessage = "이메일 인증코드가 일치하지 않습니다."
private const val SuccessToSignUpMessage = "회원가입에 성공했습니다!"
private const val SignUpConflictMessage = "이미 가입되었거나, 닉네임이 이미 존재합니다."
private const val EmailValidMessage = "올바른 이메일 형식을 입력해주세요."
private const val SuccessToSendEmail = "이메일 코드를 전송했습니다."

/**
 * SimTong의 회원가입 Main Screen입니다.
 * [SignUpStep] 을 통해서 회원가입 단계를 관리합니다.
 */
@OptIn(InternalCoroutinesApi::class)
@Suppress("UnusedPrivateMember")
@Composable
internal fun SignUpScreen(
    navController: NavController,
) {
    val viewModel: SignUpViewModel = hiltViewModel()

    val container = viewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    val toast = rememberToast()

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignUpSideEffect.NavigateToSignUpName -> {
                viewModel.navigatePage(SIGN_UP_NAME)
            }
            is SignUpSideEffect.NavigateToSignUpVerify -> {
                viewModel.navigatePage(SIGN_UP_VERIFY)
                toast(
                    message = "${it.email}로" + SuccessToSendEmail,
                )
            }
            is SignUpSideEffect.NavigateToSignUpPassword -> {
                viewModel.navigatePage(SIGN_UP_PASSWORD)
            }
            is SignUpSideEffect.NavigateToSignUpNickName -> {
                viewModel.navigatePage(SIGN_UP_NICKNAME)
            }
            is SignUpSideEffect.UserInfoMatchingFailed -> {
                viewModel.inputFieldErrEmployeeNumber(UserInfoNotMatchingMessage)
            }
            is SignUpSideEffect.ChangeStepToInputEmail -> {
                viewModel.inputFieldErrEmployeeNumber(null)
                viewModel.navigateNameStep(SignUpStep.InputUserInfo.EMAIL)
            }
            is SignUpSideEffect.EmailVerifyAlready -> {
                toast(
                    message = EmailAlreadyVerifyMessage,
                )
                viewModel.navigatePage(SIGN_UP_PASSWORD)
            }
            is SignUpSideEffect.TooManyRequest -> {
                toast(
                    message = EmailVerifyCountOverMessage,
                )
            }
            is SignUpSideEffect.EmailCodeNotCorrect -> {
                viewModel.inputFieldErrVerifyCode(
                    message = EmailVerifyCodeNotCorrectMessage,
                )
            }
            is SignUpSideEffect.NavigateToHome -> {
                toast(
                    message = SuccessToSignUpMessage,
                )
                navController.navigate(
                    route = SimTongScreen.Home.MAIN,
                ) {
                    popUpTo(route = SimTongScreen.Auth.SIGN_UP) {
                        inclusive = true
                    }
                }
            }
            is SignUpSideEffect.SignUpConflict -> {
                toast(
                    message = SignUpConflictMessage,
                )
                navController.popBackStack()
            }
            is SignUpSideEffect.EmailValid -> {
                viewModel.inputFieldErrEmail(
                    message = EmailValidMessage,
                )
            }
            is SignUpSideEffect.ProfileImageSizeLimit -> {
                toast(
                    message = "파일 크기가 제한을 초과했습니다. (제한: ${ImageLimitSizeInKB}KB, 현재: $it)",
                )
            }
        }
    }

    Crossfade(targetState = state.currentPage) { page ->
        when (page) {
            SIGN_UP_NAME -> {
                SignUpNameScreen(
                    toPrevious = { navController.popBackStack() },
                    verificationEmployeeNumber = {
                        viewModel.verificationEmployee(
                            name = state.name,
                            employeeNumber = state.employeeNumber,
                        )
                    },
                    name = state.name,
                    onNameChanged = { viewModel.changeName(it) },
                    employeeNumber = state.employeeNumber,
                    fieldErrEmployeeNumber = state.fieldErrEmployeeNumber,
                    onEmployeeNumberChanged = { viewModel.changeEmployeeNumber(it) },
                    email = state.email,
                    fieldErrEmail = state.fieldErrEmail,
                    onEmailChanged = { viewModel.changeEmail(it) },
                    signUpNameStep = state.signUpNameStep,
                    navigatePage = { viewModel.navigateNameStep(it) },
                    sendVerifyCode = { viewModel.sendEmailCode() },
                )
            }
            SIGN_UP_VERIFY -> {
                SignUpVerifyScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_NAME) },
                    checkVerifyCode = {
                        viewModel.checkVerifyCode(
                            email = state.email,
                            emailCode = state.verifyCode,
                        )
                    },
                    verifyCode = state.verifyCode,
                    onVerifyCodeChanged = { viewModel.changeVerifyCode(it) },
                    fieldErrEmailCode = state.fieldErrVerifyCode,
                )
            }
            SIGN_UP_PASSWORD -> {
                SignUpPasswordScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_VERIFY) },
                    toNext = { viewModel.navigatePage(SIGN_UP_NICKNAME) },
                    signUpPasswordStep = state.signUpPasswordStep,
                    password = state.password,
                    onPasswordChanged = { viewModel.changePassword(it) },
                    checkPassword = state.checkPassword,
                    onCheckPasswordChanged = { viewModel.changeCheckPassword(it) },
                    navigatePage = { viewModel.navigatePasswordStep(it) },
                )
            }
            SIGN_UP_NICKNAME -> {
                SignUpNicknameScreen(
                    toPrevious = { viewModel.navigatePage(SIGN_UP_PASSWORD) },
                    signUp = {
                        viewModel.signUp(
                            name = state.name,
                            employeeNumber = state.employeeNumber,
                            email = state.email,
                            password = state.password,
                            nickname = state.nickname,
                            profileImage = state.profileImg,
                        )
                    },
                    onProfileImgChanged = { viewModel.changeProfileImg(it) },
                    nickname = state.nickname,
                    onNicknameChanged = { viewModel.changeNickname(it) },
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
