@file:Suppress("ktlintMainSourceSetCheck")

package com.comit.feature_home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.comit.feature_home.screen.HomeScreen
import com.comit.feature_home.screen.SalaryWebViewScreen
import com.comit.feature_home.screen.alarm.AlarmScreen
import com.comit.feature_home.screen.closeday.WriteClosedDayScreen
import com.comit.feature_home.screen.schedule.ShowScheduleScreen
import com.comit.feature_home.screen.schedule.WriteScheduleScreen
import com.comit.navigator.SimTongScreen

fun NavGraphBuilder.homeNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = SimTongScreen.Home.MAIN,
        route = SimTongScreen.Home.route,
    ) {
        composable(
            route = SimTongScreen.Home.MAIN,
        ) {
            HomeScreen(
                navController = navController,
            )
        }
        composable(
            route = SimTongScreen.Home.CLOSE_DAY,
        ) {
            WriteClosedDayScreen(
                navController = navController,
            )
        }
        composable(
            route = SimTongScreen.Home.SHOW_SCHEDULE,
        ) {
            ShowScheduleScreen(
                navController = navController,
            )
        }
        composable(
            route = SimTongScreen.Home.WRITE_SCHEDULE +
                "isNew{isNew}" + "scheduleId{scheduleId}" +
                "title{title}" + "scheduleStart{scheduleStart}" +
                "scheduleEnd{scheduleEnd}",
            arguments = listOf(
                navArgument(name = "isNew") {
                    type = NavType.BoolType
                    defaultValue = false
                },
                navArgument(name = "scheduleId") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("title") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("scheduleStart") {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument("scheduleEnd") {
                    type = NavType.StringType
                    defaultValue = ""
                },
            )
        ) {

            val isNew = it.arguments?.getBoolean("isNew") ?: false
            val scheduleId = it.arguments?.getString("scheduleId") ?: ""
            val title = it.arguments?.getString("title") ?: ""
            val scheduleStart = it.arguments?.getString("scheduleStart") ?: ""
            val scheduleEnd = it.arguments?.getString("scheduleEnd") ?: ""

            WriteScheduleScreen(
                navController = navController,
                isNew = isNew,
                scheduleId = scheduleId,
                title = title,
                scheduleStart = scheduleStart,
                scheduleEnd = scheduleEnd,
            )
        }
        composable(
            route = SimTongScreen.Home.SALARY,
        ) {
            SalaryWebViewScreen()
        }
        composable(
            route = SimTongScreen.Home.ALARM,
        ) {
            AlarmScreen(navController = navController)
        }
    }
}
