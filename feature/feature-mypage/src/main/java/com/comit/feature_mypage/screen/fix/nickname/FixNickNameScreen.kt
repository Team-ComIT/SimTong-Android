package com.comit.feature_mypage.screen.fix.nickname

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.component.SimTongTextField
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixNickNameSideEffect
import com.comit.feature_mypage.screen.fix.FixBaseScreen
import kotlinx.coroutines.InternalCoroutinesApi

private const val NickNameFormException = "사용할 수 없는 닉네임입니다"

private const val TokenException = "토큰 만료 다시 로그인해주세요"

private const val SameNickNameException = "이미 존재하는 닉네임입니다"

private const val ServerException = "서버와 통신에 실패했습니다"

@OptIn(InternalCoroutinesApi::class)
@Composable
internal fun FixNickNameScreen(
    navController: NavController,
    vm: FixNickNameViewModel = hiltViewModel(),
) {
    val fixNickNameContainer = vm.container
    val fixNickNameInState = fixNickNameContainer.stateFlow.collectAsState().value
    val fixNickNameSideEffect = fixNickNameContainer.sideEffectFlow

    fixNickNameSideEffect.observeWithLifecycle() {
        when (it) {
            FixNickNameSideEffect.FixNickNameSuccess -> {
                navController.popBackStack()
            }
            FixNickNameSideEffect.NickNameFormException -> {
                vm.inPutErrMsgNickName(msg = NickNameFormException)
            }
            FixNickNameSideEffect.TokenException -> {
                vm.inPutErrMsgNickName(msg = TokenException)
            }
            FixNickNameSideEffect.SameNickNameException -> {
                vm.inPutErrMsgNickName(msg = SameNickNameException)
            }
            FixNickNameSideEffect.ServerException -> {
                vm.inPutNickName(msg = ServerException)
            }
        }
    }

    FixBaseScreen(
        header = stringResource(id = R.string.nick_name_input),
        onPrevious = {
            navController.popBackStack()
        },
        btnText = stringResource(id = R.string.nick_name_fix),
        onNext = { vm.fixNickName(fixNickNameInState.nickname) },
        btnEnabled = fixNickNameInState.nickname.isNotEmpty(),
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        SimTongTextField(
            value = fixNickNameInState.nickname,
            onValueChange = {
                vm.inPutNickName(it)
                vm.inPutErrMsgNickName(null)
            },
            title = stringResource(id = R.string.nick_name_input),
            error = fixNickNameInState.errNicknameMsg,
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
