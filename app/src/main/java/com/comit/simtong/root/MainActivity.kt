package com.comit.simtong.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.comit.common.systemBarPaddings
import com.comit.core_design_system.theme.SimTongTheme
import com.comit.feature_auth.navigation.authNavigation
import com.comit.feature_home.navigation.homeNavigation
import com.comit.feature_mypage.navigation.myPageNavigation
import com.comit.navigator.SimTongRoute
import com.comit.simtong.handler.NetworkConnection
import com.comit.simtong.handler.SimTongExceptionHandler
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val networkCheck: NetworkConnection by lazy {
        NetworkConnection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen()

        networkCheck.register()

        setContent {
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
                        .padding(systemBarPaddings),
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

    override fun onDestroy() {
        super.onDestroy()

        networkCheck.unregister()
    }
}
