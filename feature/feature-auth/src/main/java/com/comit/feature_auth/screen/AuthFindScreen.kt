package com.comit.feature_auth.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.TabBar
import com.comit.core_design_system.component.TabItem
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.feature_auth.R
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun AuthFindScreen(

){
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
                .height(35.dp),
            backgroundColor = SimTongColor.OtherColor.WhiteF2,
            filters = listOf(
                TabItem("사원번호 찾기"),
                TabItem("비밀번호 찾기")
            )
        )

        FindEmployeeNum()

    }
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun PreviewAuthFindScreen() {
    AuthFindScreen()
}