package com.comit.feature_auth.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.comit.feature_auth.screen.error.ErrorScreen
import com.comit.feature_auth.screen.find.AuthFindScreen
import com.comit.feature_auth.screen.signIn.SignInScreen
import com.comit.feature_auth.screen.signUp.SignUpScreen
import com.comit.feature_auth.screen.splash.StartScreen
import com.comit.feature_auth.utils.AuthDeepLinkKeyUtil
import com.comit.navigator.SimTongRoute
import com.comit.navigator.SimTongScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
fun NavGraphBuilder.authNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = SimTongScreen.Auth.SPLASH,
        route = SimTongRoute.Auth.name,
    ) {
        composable(
            route = SimTongScreen.Auth.SPLASH,
        ) {
            StartScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.Auth.SIGN_IN,
        ) {
            SignInScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.Auth.SIGN_UP
        ) {
            SignUpScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.Auth.AUTH_FIND,
        ) {
            AuthFindScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.Auth.ERROR + "/?${AuthDeepLinkKeyUtil.MESSAGE}={message}",
            arguments = listOf(
                navArgument(AuthDeepLinkKeyUtil.MESSAGE) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) { backStackEntry ->
            val message = backStackEntry.arguments?.getString(AuthDeepLinkKeyUtil.MESSAGE) ?: ""

            ErrorScreen(
                navController = navController,
                errorMessage = message,
            )
        }
    }
}
