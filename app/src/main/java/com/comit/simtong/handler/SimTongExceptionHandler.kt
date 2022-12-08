package com.comit.simtong.handler

import android.content.Context
import android.widget.Toast
import androidx.navigation.NavController
import com.comit.domain.exception.NeedLoginException
import com.comit.domain.exception.NoInternetException
import com.comit.navigator.SimTongScreen
import kotlin.system.exitProcess

private const val InternetErrorMessage = "인터넷이 제대로 동작되지 않습니다."

/**
 * TODO("limsaehyun - 제대로 동작되지 않음 수정 요함 SIMT-63")
 */
class SimTongExceptionHandler(
    private val context: Context,
    private val navController: NavController,
) : Thread.UncaughtExceptionHandler {
    override fun uncaughtException(t: Thread, e: Throwable) {

        when (e) {
            is NoInternetException -> {
                Toast.makeText(context, InternetErrorMessage, Toast.LENGTH_SHORT).show()
            }
            is NeedLoginException -> {
                navController.navigate(
                    route = SimTongScreen.Auth.SIGN_IN,
                )
            }
        }

        exitProcess(2)
    }
}