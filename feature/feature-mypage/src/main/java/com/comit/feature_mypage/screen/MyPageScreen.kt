package com.comit.feature_mypage.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.RedIconButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body5
import com.comit.feature_mypage.R
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
fun MyPageScreen() {
    SimTongTheme(
        darkTheme = false
    ) {

        var editMode by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Header(
                headerText = stringResource(id = R.string.eng_my_page),
                enabledBackBtn = true,
                onPrevious = {},
                sideBtnText = stringResource(id = R.string.kr_edit)
            ) {
                editMode = !editMode
            }

            Spacer(modifier = Modifier.height(22.dp))

            MyPageProfileImage(imageUrl = MyPageFakeData.image) {
            }

            if (!editMode) {
                Spacer(modifier = Modifier.height(90.dp))

                MyPageDescription(
                    title = stringResource(id = R.string.kr_name),
                    content = MyPageFakeData.name
                )
                MyPageDescription(
                    title = stringResource(id = R.string.kr_nickname),
                    content = MyPageFakeData.nickname
                )
                MyPageDescription(
                    title = stringResource(id = R.string.eng_email),
                    content = MyPageFakeData.email
                )
                MyPageDescription(
                    title = stringResource(id = R.string.kr_workplace),
                    content = MyPageFakeData.workplace
                )
                MyPageDescription(
                    title = stringResource(id = R.string.kr_change_password),
                    content = MyPageFakeData.changePassword
                )
            } else {
                Spacer(modifier = Modifier.height(19.dp))

                Body3(text = MyPageFakeData.nickname, color = SimTongColor.Black)

                Spacer(modifier = Modifier.height(4.dp))

                Body5(text = MyPageFakeData.nickname, color = SimTongColor.Gray400)

                Spacer(modifier = Modifier.height(45.dp))

                Column(
                    verticalArrangement = Arrangement.spacedBy(29.dp)
                ) {
                    MyPageEditModeMenu(
                        title = stringResource(id = R.string.kr_my_page_edit_my_information),
                        content = stringResource(id = R.string.kr_my_page_edit_my_information_description),
                        enabledNextIcon = true,
                    ) {
                    }

                    MyPageEditModeMenu(
                        title = stringResource(id = R.string.kr_my_page_sign_in),
                        titleColor = SimTongColor.MainColor,
                        content = stringResource(
                            id = R.string.kr_my_page_sign_in_description
                        ),
                    )
                }
            }
        }
    }
}

@Composable
fun MyPageProfileImage(
    imageUrl: String,
    onClickAddBtn: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(80.dp)
    ) {
        GlideImage(imageModel = imageUrl, Modifier.clip(CircleShape))

        RedIconButton(
            painter = painterResource(id = R.drawable.ic_my_page_plus),
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.BottomEnd)
        ) {
            onClickAddBtn()
        }
    }
}

@Composable
fun MyPageDescription(
    isFinal: Boolean = false,
    title: String,
    content: String,
    onClick: (() -> Unit)? = null,
) {
    Column {
        Box(
            modifier = Modifier
                .height(45.dp)
                .fillMaxWidth()
                .clickable { onClick ?: {} }
        ) {
            Body5(
                modifier = Modifier.align(Alignment.CenterStart),
                text = title,
                color = SimTongColor.Black
            )

            Body5(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = content,
                color = SimTongColor.Gray500
            )
        }

        if (!isFinal) {
            Divider(
                thickness = 1.dp,
                color = SimTongColor.Gray300
            )
        }
    }
}

@Composable
fun MyPageEditModeMenu(
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
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Body5(text = title, color = titleColor)

            Spacer(modifier = Modifier.height(4.dp))

            Body13(text = content, color = SimTongColor.Gray400)
        }

        if (enabledNextIcon) {
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                painter = painterResource(id = SimTongIcons.Next),
                contentDescription = null
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    MyPageScreen()
}
