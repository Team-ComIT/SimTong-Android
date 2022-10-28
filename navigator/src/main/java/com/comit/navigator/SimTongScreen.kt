package com.comit.navigator

sealed class SimTongScreen(
    val route: String,
) {
    object Auth: SimTongScreen("Auth") {
        const val SIGN_IN = "SING_IN"
        const val SIGN_UP = "SIGN_UP"
        const val AUTH_FIND = "AUTH_FIND"
    }

    object Home: SimTongScreen("Home") {
        const val MAIN = "HOME_MAIN"
    }

    object MyPage: SimTongScreen("MyPage") {
        const val MAIN = "MyPage_MAIN"
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
