package com.comit.feature_mypage.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.button.SimTongIconButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body5
import com.comit.feature_mypage.R
import com.comit.navigator.SimTongScreen
import com.skydoves.landscapist.glide.GlideImage

/**
 * MyPage UI 구현을 위한 fake data
 */
object MyPageFakeData {

    const val image = "https://avatars.githubusercontent.com/u/27887884?v=4"
    const val name = "임세현"
    const val nickname = "skeat"
    const val email = "sh007100@naver.com"
    const val workplace = "성심당 본점"
    const val changePassword = "****"
}

/**
 * SimTong의 [MyPageScreen] 구현합니다.
 * [TODO]
 * 현재 MyPageScreen 은 더미 데이터를 통해 UI만 구현된 상태입니다.
 * 또한 UI쪽에서는 click effect가 자연스럽지 않습니다.
 * 이는 추후에 개선되어야 합니다.
 */

@Composable
fun MyPageScreen(
    navController: NavController,
    vm: MyPageViewModel = hiltViewModel(),
) {
    val myPageContainer = vm.container
    val myPageInState = myPageContainer.stateFlow.collectAsState().value

    LaunchedEffect(key1 = vm) {
        vm.fetchUserInformation()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Header(
            headerText = stringResource(
                id = R.string.eng_my_page,
            ),
            enabledBackBtn = true,
            enabledTextBtn = true,
            onPrevious = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(horizontal = 20.dp)
        )

        Spacer(modifier = Modifier.height(22.dp))

        MyPageProfileImage(
            imageUrl = myPageInState.profileImagePath,
        ) {
            // TODO ("갤러리 접근")
        }
        Spacer(modifier = Modifier.height(80.dp))

        MyPageDescriptionNoClickable(
            title = stringResource(
                id = R.string.kr_name,
            ),
            content = myPageInState.name,
        )
        MyPageDescription(
            title = stringResource(
                id = R.string.kr_nickname,
            ),
            content = myPageInState.nickname,
            onClick = {
                navController.navigate(
                    route = SimTongScreen.MyPage.FIX_NICKNAME,
                )
            }
        )
        MyPageDescription(
            title = stringResource(
                id = R.string.eng_email,
            ),
            content = myPageInState.email,
            onClick = {
                navController.navigate(
                    route = SimTongScreen.MyPage.FIX_EMAIL,
                )
            }
        )
        MyPageDescription(
            title = stringResource(
                id = R.string.kr_workplace,
            ),
            content = myPageInState.spot,
            onClick = {
                navController.navigate(
                    route = SimTongScreen.MyPage.FIX_WORKPLACE,
                )
            }
        )
        MyPageDescriptionImage(
            title = stringResource(
                id = R.string.password_fix
            ),
            color = SimTongColor.Gray800,
            onClick = {
                navController.navigate(
                    route = SimTongScreen.MyPage.FIX_PASSWORD
                )
            }
        )
        MyPageDescriptionImage(
            title = stringResource(
                id = R.string.kr_my_page_sign_in
            ),
            color = SimTongColor.MainColor300,
            onClick = {
                // TODO: Token 삭제, 전체 popUp
                navController.navigate(
                    route = SimTongScreen.Auth.SIGN_IN
                ) {
                    popUpTo(route = SimTongScreen.MyPage.MAIN) {
                        inclusive = true
                    }
                    popUpTo(route = SimTongScreen.Home.MAIN) {
                        inclusive = true
                    }
                }
            }
        )
    }
}

@Composable
private fun MyPageProfileImage(
    imageUrl: String,
    onClickAddBtn: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(80.dp),
    ) {
        GlideImage(
            imageModel = imageUrl,
            modifier = Modifier.clip(CircleShape),
            error = painterResource(id = SimTongIcon.Profile_Big.drawableId)
        )

        SimTongIconButton(
            painter = painterResource(
                id = R.drawable.ic_my_page_plus
            ),
            contentDescription = stringResource(
                id = R.string.ic_plus_description,
            ),
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomEnd),
        ) {
            onClickAddBtn()
        }
    }
}

@Composable
private fun MyPageDescriptionNoClickable(
    title: String,
    content: String,
) {
    Row(
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Body5(
            text = title,
            color = SimTongColor.Gray800
        )

        Spacer(modifier = Modifier.weight(1f))

        Body5(
            text = content,
            color = SimTongColor.Gray300
        )
    }
}

@Composable
private fun MyPageDescription(
    title: String,
    content: String,
    onClick: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .simClickable(
                onClick = onClick ?: {}
            )
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Body5(
            text = title,
            color = SimTongColor.Gray800
        )

        Spacer(modifier = Modifier.weight(1f))

        Body5(
            text = content,
            color = SimTongColor.Gray300
        )
    }
}

@Composable
private fun MyPageDescriptionImage(
    title: String,
    color: Color,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .simClickable(
                onClick = onClick ?: {}
            )
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Body5(
            text = title,
            color = color
        )

        Image(
            painter = painterResource(
                id = SimTongIcon.Gray400_Next.drawableId
            ),
            contentDescription = SimTongIcon.Gray400_Next.contentDescription,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

/* if (!editMode) {

    )
    MyPageDescription(
        title = stringResource(
            id = R.string.kr_change_password,
        ),
        // TODO: SIMT-55
        content = "*********",
        onClick = {
            navController.navigate(
                route = SimTongScreen.MyPage.FIX_PASSWORD,
            )
        }
    )
} else {
    Spacer(modifier = Modifier.height(19.dp))

    Body3(
        text = MyPageFakeData.nickname,
        color = SimTongColor.Black,
    )

    Spacer(modifier = Modifier.height(4.dp))

    Body5(
        text = MyPageFakeData.nickname,
        color = SimTongColor.Gray300,
    )

    Spacer(modifier = Modifier.height(45.dp))

    Column(
        verticalArrangement = Arrangement.spacedBy(29.dp)
    ) {
        MyPageEditModeMenu(
            title = stringResource(
                id = R.string.kr_my_page_edit_my_information,
            ),
            content = stringResource(
                id = R.string.kr_my_page_edit_my_information_description,
            ),
            enabledNextIcon = true,
        ) {
        }

        MyPageEditModeMenu(
            title = stringResource(id = R.string.kr_my_page_sign_in),
            titleColor = SimTongColor.MainColor,
            content = stringResource(
                id = R.string.kr_my_page_sign_in_description,
            ),
        )
    } */

/* @Composable
private fun MyPageEditModeMenu(
    title: String,
    titleColor: Color = SimTongColor.Black,
    content: String,
    enabledNextIcon: Boolean = false,
    onClick: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick ?: {})
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterStart),
        ) {
            Body5(
                text = title,
                color = titleColor,
            )

            Spacer(modifier = Modifier.height(4.dp))

            Body13(
                text = content,
                color = SimTongColor.Gray300,
            )
        }

        if (enabledNextIcon) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(
                    id = SimTongIcon.Next.drawableId,
                ),
                contentDescription = SimTongIcon.Next.contentDescription,
            )
        }
    }
} */

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen(
        navController = rememberNavController()
    )
}
