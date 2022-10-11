package com.comit.core_design_system.modifier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import com.comit.core_design_system.util.runIf

@Stable
internal fun Modifier.simClickable(
    rippleEnabled: Boolean = true,
    rippleColor: Color? = null,
    onClick: (() -> Unit)?,
) = runIf(onClick != null) {
    composed {
        clickable(
            onClick = onClick!!,
            indication = rememberRipple(
                color = rippleColor ?: Color.Unspecified,
            ).takeIf {
                rippleEnabled
            },
            interactionSource = remember { MutableInteractionSource() },
        )
    }
}