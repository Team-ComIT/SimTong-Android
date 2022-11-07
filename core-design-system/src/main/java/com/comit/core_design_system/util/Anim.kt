package com.comit.core_design_system.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable

@Composable
fun AnimatedScreenSlide(
    visible: Boolean,
    enterDuration: Int = 200,
    fadeDuration: Int = 500,
    context: @Composable (String) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = enterDuration)) {
            -it
        } + fadeIn(
            animationSpec = tween(durationMillis = fadeDuration)
        ),
        exit = slideOutHorizontally(animationSpec = tween(durationMillis = enterDuration)) {
            -it
        } + fadeOut(
            animationSpec = tween(durationMillis = fadeDuration)
        )
    ) {
        context("")
    }
}
