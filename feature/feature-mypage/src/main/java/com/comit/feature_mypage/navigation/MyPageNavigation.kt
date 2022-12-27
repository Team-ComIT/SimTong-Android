package com.comit.feature_mypage.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.comit.feature_mypage.screen.MyPageScreen
import com.comit.feature_mypage.screen.PrivacyPolicyScreen
import com.comit.feature_mypage.screen.fix.email.FixEmailInputCertificationScreen
import com.comit.feature_mypage.screen.fix.email.FixEmailScreen
import com.comit.feature_mypage.screen.fix.nickname.FixNickNameScreen
import com.comit.feature_mypage.screen.fix.password.FixPassword
import com.comit.feature_mypage.screen.fix.workplace.FixWorkPlaceScreen
import com.comit.feature_mypage.utils.MyPageDeepLinkKeyUtil
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

        composable(
            route = SimTongScreen.MyPage.FIX_EMAIL,
        ) {
            FixEmailScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.MyPage.INPUT_CERTIFICATION_NUMBER + MyPageDeepLinkKeyUtil.EMAIL + "{email}",
            arguments = listOf(
                navArgument(MyPageDeepLinkKeyUtil.EMAIL) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            ),
        ) {
            val email = it.arguments?.getString("email") ?: ""

            FixEmailInputCertificationScreen(
                navController = navController,
                email = email,
            )
        }

        composable(
            route = SimTongScreen.MyPage.FIX_NICKNAME,
        ) {
            FixNickNameScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.MyPage.FIX_PASSWORD,
        ) {
            FixPassword(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.MyPage.FIX_WORKPLACE,
        ) {
            FixWorkPlaceScreen(
                navController = navController,
            )
        }

        composable(
            route = SimTongScreen.MyPage.PRIVACY_POLICY,
        ) {
            PrivacyPolicyScreen {
                navController.popBackStack()
            }
        }
    }
}
