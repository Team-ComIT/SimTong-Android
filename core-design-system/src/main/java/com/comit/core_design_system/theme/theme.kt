package com.comit.core_design_system.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.constans.RoundShapes

@SuppressLint("ConflictingOnColor")
private val LightColorPalette = lightColors(
    primary = SimTongColor.Primary,
    secondary = SimTongColor.MainColor,
    background = SimTongColor.White,
    surface = SimTongColor.White,
    error = SimTongColor.Error,
    onPrimary = SimTongColor.White,
    onSecondary = SimTongColor.Primary,
    onBackground = SimTongColor.Primary,
    onSurface = SimTongColor.Primary,
    onError = SimTongColor.White
)

private val DarkColorPalette = darkColors(
    primary = SimTongColor.Primary,
    secondary = SimTongColor.MainColor,
    background = SimTongColor.Primary,
    surface = SimTongColor.Primary,
    error = SimTongColor.Error,
    onPrimary = SimTongColor.White,
    onSecondary = SimTongColor.Primary,
    onBackground = SimTongColor.White,
    onSurface = SimTongColor.White,
    onError = SimTongColor.White
)

@Composable
fun SimTongTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = RoundShapes,
        content = content
    )
}
