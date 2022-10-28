package com.comit.feature_mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.comit.feature_mypage.screen.MyPageScreen
import com.comit.navigator.SimTongRoute
import com.comit.navigator.SimTongScreen

fun NavGraphBuilder.myPageNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = SimTongScreen.MyPage.MAIN,
        route = SimTongRoute.MyPage.name,
    ) {
        composable(
            route = SimTongScreen.MyPage.MAIN,
        ) {
            MyPageScreen(
                navController = navController,
            )
        }
    }
}