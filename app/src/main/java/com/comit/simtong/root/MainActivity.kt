package com.comit.simtong.root

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.comit.common.systemBarPadding
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.feature_auth.navigation.authNavigation
import com.comit.feature_home.navigation.homeNavigation
import com.comit.feature_mypage.navigation.myPageNavigation
import com.comit.navigator.SimTongRoute
import com.comit.simtong.handler.SimTongExceptionHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            setWindowInset()
            saveDeviceToken(hiltViewModel())

            val navController = rememberNavController()

            Thread.setDefaultUncaughtExceptionHandler(
                SimTongExceptionHandler(
                    context = this,
                    navController = navController,
                )
            )

            SimTongTheme(
                darkTheme = false,
            ) {
                NavHost(
                    modifier = Modifier
                        .systemBarPadding(),
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

    @Suppress("MagicNumber")
    @Composable
    private fun setWindowInset() {
        window.statusBarColor = MaterialTheme.colors.surface.toArgb()
        window.navigationBarColor = MaterialTheme.colors.surface.toArgb()

        @Suppress("DEPRECATION")
        if (MaterialTheme.colors.surface.luminance() > 0.5f) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (MaterialTheme.colors.surface.luminance() > 0.5f) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    private fun saveDeviceToken(
        vm: MainViewModel,
    ) {
        vm.saveDeviceToken()
    }
}
