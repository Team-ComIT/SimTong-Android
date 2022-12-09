package com.comit.navigator

sealed class SimTongScreen(
    val route: String,
) {
    object Auth : SimTongScreen("Auth") {
        const val SPLASH = "SPLASH"
        const val SIGN_IN = "SING_IN"
        const val SIGN_UP = "SIGN_UP"
        const val AUTH_FIND = "AUTH_FIND"
    }

    object Home : SimTongScreen("Home") {
        const val MAIN = "HOME_MAIN"
        const val CLOSE_DAY = "CLOSE_DAY"
        const val SHOW_SCHEDULE = "SHOW_SCHEDULE"
        const val WRITE_SCHEDULE = "WRITE_SCHEDULE"
    }

    object MyPage : SimTongScreen("MyPage") {
        const val MAIN = "MYPAGE_MAIN"
        const val FIX_EMAIL = "FIX_EMAIL"
        const val INPUT_CERTIFICATION_NUMBER = "INPUT_CERTIFICATION_NUMBER"
        const val FIX_NICKNAME = "FIX_NICKNAME"
        const val FIX_PASSWORD = "FIX_PASSWORD"
        const val FIX_WORKPLACE = "FIX_WORKPLACE"
    }
}

sealed class SimTongRoute(
    val name: String,
) {
    object Auth : SimTongRoute(
        name = "auth",
    )

    object Home : SimTongRoute(
        name = "home",
    )

    object MyPage : SimTongRoute(
        name = "myPage",
    )
}
