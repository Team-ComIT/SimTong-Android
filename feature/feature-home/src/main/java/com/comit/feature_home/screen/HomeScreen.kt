@file:OptIn(ExperimentalMaterialApi::class)

package com.comit.feature_home.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
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
import com.comit.core_design_system.modifier.noRippleClickable
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Title3
import com.comit.core_design_system.util.currentMealsTime
import com.comit.navigator.SimTongScreen
import com.example.feature_home.R

object HomeFakeData {
    val foodList = listOf(
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유",
        "누룽지\n돼지불고기\n마늘\n배추김치\n달콤핫붓새빵\n바나나/딸기우유"
    )
}

private val HomeScreenTopRowHeight: Dp = 34.dp

@Stable
private val HomeScreenPadding = PaddingValues(
    horizontal = 25.dp
)

@Stable
private val HomeBottomIconLayoutShape = RoundedCornerShape(
    size = 4.dp,
)

private val HomeCalendarHeight: Dp = 422.dp

private val HomeBottomIconLayoutElevation: Dp = 2.dp

private val HomeBottomIconLayoutHeight: Dp = 48.dp

private val HomeBottomIconLayoutImageSize: Dp = 28.dp

@Composable
fun HomeScreen(
    navController: NavController,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(HomeScreenPadding)
    ) {
        Header(
            headerText = "",
            enabledBeilBtn = true,
            onBeil = {
                // TODO ("navigate notification")
            },
            enabledPeopleBtn = true,
            onMyPage = {
                navController.navigate(
                    route = SimTongScreen.MyPage.MAIN,
                )
            },
        )

        Column(
            modifier = Modifier
                .verticalScroll(scrollState),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .height(HomeScreenTopRowHeight)
                    .noRippleClickable { },
            ) {
                Title3(text = stringResource(id = R.string.calendar))

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    painter = painterResource(id = SimTongIcon.Gray_Next.drawableId),
                    contentDescription = SimTongIcon.Gray_Next.contentDescription,
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            SimTongCalendar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(HomeCalendarHeight)
            )

            Spacer(modifier = Modifier.height(30.dp))

            Title3(
                text = stringResource(
                    id = R.string.employee_menu,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            FoodList(
                timeCheck = currentMealsTime(),
                list = HomeFakeData.foodList,
            )

            Spacer(modifier = Modifier.height(27.dp))

            HomeBottomIconLayout(
                painter = painterResource(
                    id = R.drawable.ic_home_coin,
                ),
                title = stringResource(
                    id = R.string.my_pay_info,
                ),
                content = stringResource(
                    id = R.string.my_pay_info_content,
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            HomeBottomIconLayout(
                painter = painterResource(id = R.drawable.ic_home_schedule),
                title = stringResource(id = R.string.schedule_write),
                content = stringResource(id = R.string.schedule_write_content),
                onClick = {
                    navController.navigate(
                        route = SimTongScreen.Home.CLOSE_DAY,
                    )
                }
            )

            Spacer(modifier = Modifier.height(46.dp))
        }
    }
}

@Composable
private fun HomeBottomIconLayout(
    painter: Painter,
    title: String,
    content: String,
    onClick: () -> Unit = {},
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(HomeBottomIconLayoutHeight)
            .simClickable {
                onClick()
            },
        shape = HomeBottomIconLayoutShape,
        elevation = HomeBottomIconLayoutElevation,
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = SimTongColor.Transparent,
                    shape = HomeBottomIconLayoutShape,
                ),
        ) {

            Spacer(modifier = Modifier.width(12.dp))

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .size(HomeBottomIconLayoutImageSize),
            )

            Spacer(modifier = Modifier.width(11.dp))

            Column(
                modifier = Modifier
                    .height(HomeBottomIconLayoutHeight)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically),
            ) {
                Body5(
                    text = title,
                    color = SimTongColor.Gray800,
                )
                Body14(
                    text = content,
                    color = SimTongColor.Gray300,
                )
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreen(
        navController = rememberNavController(),
    )
}
