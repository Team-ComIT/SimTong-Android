package com.comit.core_design_system.modifier

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.semantics.Role
import com.comit.core_design_system.util.runIf

/**
 * Composable 에 clickable 을 설정해주는 [Modifier]
 *
 * @param rippleEnabled Ripple 여부 설정
 * @param rippleColor 표시된 Ripple Color
 * @param runIf clickable 이 발생하는 조건
 * @param singleClick 더블 클릭 방지 여부
 * @param onClick 컴포넌트가 클릭됐을 때 실행할 람다식
 *
 * @return clickable 이 적용된 [Modifier]
 */
@OptIn(ExperimentalFoundationApi::class)
fun Modifier.simClickable(
    rippleEnabled: Boolean = true,
    rippleColor: Color? = null,
    runIf: Boolean = true,
    singleClick: Boolean = true,
    onLongClick: (() -> Unit)? = null,
    onClick: (() -> Unit)?,
) = runIf(runIf) {
    composed {
        val multipleEventsCutter = remember { MultipleEventsCutter.get() }

        combinedClickable(
            onClick = {
                onClick?.let {
                    if (singleClick) {
                        multipleEventsCutter.processEvent {
                            it()
                        }
                    } else {
                        it()
                    }
                }
            },
            onLongClick = onLongClick,
            indication = rememberRipple(
                color = rippleColor ?: Color.Unspecified,
            ).takeIf {
                rippleEnabled
            },
            interactionSource = remember { MutableInteractionSource() },
        )
    }
}

fun Modifier.simSelectable(
    selected: Boolean,
    rippleEnabled: Boolean = true,
    rippleColor: Color? = null,
    enabled: Boolean = true,
    role: Role? = null,
    onClick: () -> Unit,
) = composed(
    inspectorInfo = debugInspectorInfo {
        name = "selectable"
        properties["selected"] = selected
        properties["enabled"] = enabled
        properties["role"] = role
        properties["onClick"] = onClick
    }
) {
    Modifier.selectable(
        selected = selected,
        enabled = enabled,
        role = role,
        interactionSource = remember { MutableInteractionSource() },
        indication = rememberRipple(
            color = rippleColor ?: Color.Unspecified,
        ).takeIf {
            rippleEnabled
        },
        onClick = onClick
    )
}
