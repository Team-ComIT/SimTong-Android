package com.comit.simtong.handler

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.exception.NoConnectivityException
import com.comit.domain.exception.NoInternetException
import com.comit.domain.exception.UnknownException
import com.comit.feature_auth.utils.AuthDeepLinkKeyUtil
import com.comit.navigator.SimTongScreen
import kotlin.system.exitProcess

private const val InternetErrorMessage = "인터넷 연결상태가 불안정합니다.\n확인 후 다시 시도해주세요."

class SimTongExceptionHandler(
    private val context: Context,
    private val navController: NavController,
) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {

        when (e) {
            is NoInternetException -> {
                Toast.makeText(context, InternetErrorMessage, Toast.LENGTH_SHORT).show()
            }

            is NoConnectivityException -> {
                Toast.makeText(context, InternetErrorMessage, Toast.LENGTH_SHORT).show()
            }

            is NeedLoginException -> {
                navController.navigate(
                    route = SimTongScreen.Auth.SIGN_IN,
                ) {
                    popUpTo(0)
                }
            }
            is UnknownException -> {
                navController.navigate(
                    route = SimTongScreen.Auth.ERROR + "/?${AuthDeepLinkKeyUtil.MESSAGE}=${e.message}",
                ) {
                    launchSingleTop = true
                }
            }

            else -> {
                exitProcess(2)
            }
        }
    }
}
