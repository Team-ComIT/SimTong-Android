package com.comit.feature_auth.screen.find

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.TabBar
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.feature_auth.R
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun ChangeAuthScreen(
    index: Int
) {
    when (index) {
        0 -> FindEmployeeNum()
        1 -> FindPassword()
    }
}

@Stable
val AuthFindScreenTabBarHeight = 35.dp

@ExperimentalPagerApi
@Composable
fun AuthFindScreen(

) {

    Column() {
        Image(
            painter = painterResource(id = SimTongIcons.Back(false)),
            contentDescription = stringResource(id = R.string.description_ic_back),
            modifier = Modifier
                .padding(start = 26.dp, top = 22.5.dp)
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
            changeScreen = { ChangeAuthScreen(index = it) }

        )

    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewAuthFindScreen() {
    AuthFindScreen()
}