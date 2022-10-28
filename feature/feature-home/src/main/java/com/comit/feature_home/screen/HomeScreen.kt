package com.comit.feature_home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.FoodList
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Title3
import com.comit.core_design_system.util.currentMealsTime
import com.example.feature_home.R

object HomeFakeData {
    val foodList = listOf(
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유"
    )
}

@Stable
private val HomeScreenCalendarHeight: Dp = 360.dp

@Stable
private val HomeScreenTopRowHeight: Dp = 30.dp

@Composable
fun HomeScreen(
    navController: NavController,
) {
    SimTongTheme(
        darkTheme = false
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .padding(horizontal = 25.dp)
        ) {
            Header(
                headerText = "",
                enabledBeilBtn = true,
                onBeil = {},
                enabledPeopleBtn = true,
                onMyPage = {},
            )
            Row(
                modifier = Modifier
                    .height(HomeScreenTopRowHeight)
            ) {
                Title3(
                    text = stringResource(id = R.string.calendar),
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )

                Spacer(modifier = Modifier.width(8.dp))

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    Icon(
                        painter = painterResource(id = SimTongIcon.Gray_Next.drawableId),
                        contentDescription = SimTongIcon.Gray_Next.contentDescription,
                    )
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Box(
                modifier = Modifier
                    .height(HomeScreenCalendarHeight)
            )

            Spacer(modifier = Modifier.height(9.dp))

            Title3(text = stringResource(id = R.string.employee_menu))

            Spacer(modifier = Modifier.height(16.dp))

            // TODO: FoodList 디자인 시스템 lineHeight 수정 작업 필요
            FoodList(
                timeCheck = currentMealsTime(),
                list = HomeFakeData.foodList
            )

            Spacer(modifier = Modifier.height(27.dp))

            HomeUnderRowItem(
                painter = painterResource(id = R.drawable.ic_home_coin),
                title = stringResource(id = R.string.my_pay_info),
                content = stringResource(id = R.string.my_pay_info_content)
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeUnderRowItem(
                painter = painterResource(id = R.drawable.ic_home_schedule),
                title = stringResource(id = R.string.schedule_write),
                content = stringResource(id = R.string.schedule_write_content)
            )

            Spacer(modifier = Modifier.height(46.dp))
        }
    }
}

@Stable
private fun cardShape() = RoundedCornerShape(4.dp)

@Stable
private val HomeUnderRowItemElevation: Dp = 2.dp

@Stable
private val HomeUnderRowItemHeight: Dp = 48.dp

@Stable
private val HomeUnderRowItemImageSize: Dp = 28.dp

@Composable
fun HomeUnderRowItem(
    painter: Painter,
    title: String,
    content: String,
    onClick: () -> Unit = {}
) {
    Card(
        shape = cardShape(),
        elevation = HomeUnderRowItemElevation,
        modifier = Modifier
            .fillMaxWidth()
            .height(HomeUnderRowItemHeight)
            .simClickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = SimTongColor.Transparent,
                    shape = cardShape()
                )
        ) {

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painter,
                contentDescription = "row item image",
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .size(HomeUnderRowItemImageSize)
            )

            Spacer(modifier = Modifier.width(11.dp))

            Column(
                modifier = Modifier
                    .height(HomeUnderRowItemHeight)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
            ) {
                Body5(
                    text = title,
                    color = SimTongColor.Gray900
                )
                Body14(
                    text = content,
                    color = SimTongColor.Gray400
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyPageScreen() {
    HomeScreen(
        navController = rememberNavController(),
    )
}
