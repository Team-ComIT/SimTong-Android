package com.comit.feature_home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.comit.feature_home.screen.HolidayWebViewScreen
import com.comit.feature_home.screen.HomeScreen
import com.comit.feature_home.screen.WriteClosedDayScreen
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
            route = SimTongScreen.Home.HOLIDAY,
        ) {
            HolidayWebViewScreen()
        }
    }
}
