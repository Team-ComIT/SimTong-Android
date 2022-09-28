package com.example.feature_home.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.component.FoodList
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.component.time
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.core_design_system.theme.Title3
import com.example.feature_home.R


object HomeFakeData {
    val foodList = listOf(
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유"
    )
}

@Composable
fun HomeScreen(){
    SimTongTheme(
        darkTheme = false
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(25.dp, 0.dp, 25.dp, 0.dp)
        ) {
            Row(){
                Header(
                    headerText = "",
                    enabledBeilBtn = true,
                    enabledPeopleBtn = true,
                )
            }
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .clickable { }
            ) {
                Title3(
                    text = stringResource(id = R.string.calendar),
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
                
                Spacer(modifier = Modifier.width(8.dp))

                Image(
                    painter = painterResource(id = R.drawable.ic_home_next),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .height(360.dp)
            ) {

            }

            Spacer(modifier = Modifier.height(9.dp))

            Title3(text = stringResource(id = R.string.employee_menu))

            Spacer(modifier = Modifier.height(16.dp))

            FoodList(
                timeCheck = time(),
                list = HomeFakeData.foodList
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    HomeScreen()
}
