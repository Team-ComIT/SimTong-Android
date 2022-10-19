package com.comit.feature_auth.screen.find

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.TabBar
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.feature_auth.R
import com.comit.feature_auth.screen.find.findemployeenum.FindEmployeeNum
import com.comit.feature_auth.screen.find.findemployeenum.FindPlaceLazyColumn
import com.comit.feature_auth.screen.find.password.FindPassword
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.coroutines.CoroutineScope

@Stable
val AuthFindScreenTabBarHeight = 35.dp

@ExperimentalMaterialApi
@Composable
fun ChangeAuthScreen(
    index: Int,
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
) {
    when (index) {
        0 -> FindEmployeeNum(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState
        )
        1 -> FindPassword()
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun AuthFindScreen() {
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    SimBottomSheetDialog(
        sheetState = bottomSheetState,
        sheetContent = {
            FindPlaceLazyColumn(
                coroutineScope = coroutineScope,
                bottomSheetState = bottomSheetState
            )
        }
    ) {
        AuthFindScreenBasic(
            coroutineScope = coroutineScope,
            bottomSheetState = bottomSheetState
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun AuthFindScreenBasic(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
) {
    Column() {
        Image(
            painter = painterResource(
                id = SimTongIcon.Back_Big.drawableId,
            ),
            contentDescription = stringResource(
                id = R.string.description_ic_back,
            ),
            modifier = Modifier
                .padding(
                    start = 26.dp, top = 22.5.dp,
                )
                .noRippleClickable { }
        )

        Spacer(modifier = Modifier.height(31.dp))

        TabBar(
            modifier = Modifier
                .height(AuthFindScreenTabBarHeight),
            backgroundColor = SimTongColor.OtherColor.WhiteF2,
            filters = listOf(
                stringResource(id = R.string.find_employee),
                stringResource(id = R.string.find_password)
            ),
            changeScreen = {
                ChangeAuthScreen(
                    index = it,
                    coroutineScope = coroutineScope,
                    bottomSheetState = bottomSheetState
                )
            },
        )
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewAuthFindScreen() {
    AuthFindScreen()
}
