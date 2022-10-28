package com.comit.feature_auth.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.comit.feature_auth.screen.find.AuthFindScreen
import com.comit.feature_auth.screen.signin.SignInScreen
import com.comit.feature_auth.screen.signup.SignUpScreen
import com.comit.navigator.SimTongRoute
import com.comit.navigator.SimTongScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
fun NavGraphBuilder.authNavigation(
    navController: NavController,
) {
    navigation(
        startDestination = SimTongScreen.Auth.SIGN_IN,
        route = SimTongRoute.Auth.name,
    ) {
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
    }
}