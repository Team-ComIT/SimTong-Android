package com.comit.simtong.root

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.feature_auth.navigation.authNavigation
import com.comit.feature_home.navigation.homeNavigation
import com.comit.feature_mypage.navigation.myPageNavigation
import com.comit.navigator.SimTongRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        setContent {
            val navController = rememberNavController()

            SimTongTheme(
                darkTheme = false,
            ) {
                NavHost(
                    modifier = Modifier.systemBarsPadding(),
                    navController = navController,
                    startDestination = SimTongRoute.Auth.name,
                ) {
                    authNavigation(
                        navController = navController,
                    )

                    homeNavigation(
                        navController = navController,
                    )

                    myPageNavigation(
                        navController = navController,
                    )
                }
            }
        }
    }
}
