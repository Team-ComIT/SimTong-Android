package com.comit.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

val systemBarPaddings
    @Composable get() = WindowInsets.systemBars.asPaddingValues()

fun Modifier.systemBarPadding() =
    composed {
        Modifier.windowInsetsPadding(WindowInsets.systemBars)
    }
