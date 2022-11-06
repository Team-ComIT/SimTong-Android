package com.comit.core_design_system.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable

@Composable
fun AnimatedScreenSlide(
    visible: Boolean,
    duration: Int = 200,
    context: @Composable (String) -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(animationSpec = tween(durationMillis = duration)) {
            -it
        },
        exit = slideOutHorizontally(animationSpec = tween(durationMillis = duration)) {
            -it
        }
    ) {
        context("")
    }
}
