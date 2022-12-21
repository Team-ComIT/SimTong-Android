package com.comit.feature_auth.mvi

sealed class StartState {

    object Initial: StartState()
}

sealed class StartSideEffect {

    object NavigateToHome: StartSideEffect()
}