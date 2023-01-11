@file:OptIn(ExperimentalMaterialApi::class, InternalCoroutinesApi::class)

package com.comit.feature_home.screen

import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.rememberToast
import com.comit.common.utils.string
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.component.MealList
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Title3
import com.comit.feature_home.calendar.SimTongCalendar
import com.comit.feature_home.contract.HomeSideEffect
import com.comit.feature_home.vm.HomeViewModel
import com.comit.navigator.SimTongScreen
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi

private val HomeScreenTopRowHeight: Dp = 34.dp

private val HomeScreenPadding = PaddingValues(
    horizontal = 25.dp,
)

private const val StartDateAdd = -3
private const val EndDateAdd = 3

private const val TokenException = "토큰 오류. 다시로그인해주세요"
private const val CannotWriteHoliday = "현재 휴무표를 작성할 수 있는 기간이 아닙니다"

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val scrollState = rememberScrollState()

    val toast = rememberToast()

    val container = homeViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val today = GregorianCalendar()
    val startAt = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DATE) + StartDateAdd
    )
    val startYear = startAt.get(Calendar.YEAR)
    val startMonth = (startAt.get(Calendar.MONTH) + 1)
    val startDay = startAt.get(Calendar.DATE)

    val endAt = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DATE) + EndDateAdd
    )
    val endYear = endAt.get(Calendar.YEAR)
    val endMonth = (endAt.get(Calendar.MONTH) + 1)
    val endDay = endAt.get(Calendar.DATE)

    LaunchedEffect(key1 = homeViewModel) {
        homeViewModel.fetchMenu(
            startAt = buildAnnotatedString {
                append(startYear.toString())
                append("-")
                append(string.format("%02d", startMonth))
                append("-")
                append(string.format("%02d", startDay))
            }.toString(),
            endAt = buildAnnotatedString {
                append(endYear.toString())
                append("-")
                append(string.format("%02d", endMonth))
                append("-")
                append(string.format("%02d", endDay))
            }.toString()
        )
    }

    sideEffect.observeWithLifecycle() {
        when (it) {
            HomeSideEffect.CanWriteHoliday -> {
                navController.navigate(
                    route = SimTongScreen.Home.CLOSE_DAY,
                )
            }
            HomeSideEffect.TokenException -> toast(message = TokenException)
            HomeSideEffect.CannotWriteHoliday -> toast(message = CannotWriteHoliday)
        }
    }
    Column(
        modifier = Modifier
            .padding(HomeScreenPadding)
            .verticalScroll(scrollState),
    ) {
        Header(
            headerText = "",
            enabledBeilBtn = true,
            onBeil = {
                navController.navigate(
                    route = SimTongScreen.Home.ALARM,
                )
            },
            enabledPeopleBtn = true,
            onMyPage = {
                navController.navigate(
                    route = SimTongScreen.MyPage.MAIN,
                )
            },
        )

        Row(
            modifier = Modifier
                .height(HomeScreenTopRowHeight)
                .simClickable(
                    rippleEnabled = false,
                ) {
                    navController.navigate(
                        route = SimTongScreen.Home.SHOW_SCHEDULE,
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
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
                .fillMaxWidth(),
            onCalendarClicked = {
                navController.navigate(route = SimTongScreen.Home.SHOW_SCHEDULE)
            },
            onItemClicked = { _, _ ->
                navController.navigate(route = SimTongScreen.Home.SHOW_SCHEDULE)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Title3(
            text = stringResource(
                id = R.string.employee_menu,
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        MealList(
            meals = state.mealList,
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
            onClick = {
                navController.navigate(
                    route = SimTongScreen.Home.SALARY,
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        HomeBottomIconLayout(
            painter = painterResource(id = R.drawable.ic_home_holiday),
            title = stringResource(id = R.string.schedule_write),
            content = stringResource(id = R.string.schedule_write_content),
            onClick = { homeViewModel.checkCanWriteHoliday() }
        )

        Spacer(modifier = Modifier.height(46.dp))
    }
}

@Composable
private fun HomeBottomIconLayout(
    painter: Painter,
    title: String,
    content: String,
    onClick: () -> Unit = {},
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(
                color = SimTongColor.White,
                shape = RoundedCornerShape(16.dp),
            )
            .clip(
                shape = RoundedCornerShape(16.dp)
            )
            .simClickable {
                onClick()
            }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(40.dp),
            painter = painter,
            contentDescription = null,
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Body5(text = title)
            Body14(text = content)
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
