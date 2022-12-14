package com.comit.common

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable

val systemBarPaddings
    @Composable get() = WindowInsets.systemBars.asPaddingValues()
