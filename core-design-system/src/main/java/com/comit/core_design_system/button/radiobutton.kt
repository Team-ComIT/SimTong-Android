package com.comit.core_design_system.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.util.runIf

@Composable
fun BasicRadioButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    selectedColor: Color,
    unSelectedColor: Color,
    disabledSelectedColor: Color,
    disabledUnSelectedColor: Color,
    enabled: Boolean = true,
) {
    val enabledBackgroundColor: Color by animateColorAsState(
        if (checked) selectedColor else unSelectedColor
    )

    val disabledBackgroundColor: Color by animateColorAsState(
        if (checked) disabledSelectedColor else disabledUnSelectedColor
    )

    val backgroundColor = if (enabled) enabledBackgroundColor else disabledBackgroundColor

    val inCircleSize: Dp by animateDpAsState(
        if (checked) 10.dp else 0.dp
    )

    Box(
        modifier = modifier
            .border(
                width = 2.dp,
                shape = CircleShape,
                color = backgroundColor
            )
            .runIf(enabled) {
                composed {
                    simClickable(
                        rippleEnabled = false,
                        rippleColor = null,
                    ) {
                        onCheckedChange(!checked)
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(inCircleSize)
                .background(
                    color = backgroundColor,
                    shape = CircleShape,
                )
        )
    }
}

@Stable
private val DefaultRadioButtonSize: Dp = 20.dp

@Composable
fun SimRadioButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    BasicRadioButton(
        modifier = modifier.size(DefaultRadioButtonSize),
        checked = checked,
        onCheckedChange = onCheckedChange,
        enabled = enabled,
        selectedColor = SimTongColor.MainColor,
        unSelectedColor = SimTongColor.Gray400,
        disabledSelectedColor = SimTongColor.Gray200,
        disabledUnSelectedColor = SimTongColor.Gray100,
    )
}

private val TextRadioButtonYOffset: Dp = (-1.2).dp

@Composable
fun SimTextRadioButton(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
    text: String,
) {
    Row(
        modifier = modifier
            .runIf(enabled) {
                composed {
                    simClickable(
                        rippleEnabled = false,
                        rippleColor = null,
                    ) {
                        onCheckedChange(!checked)
                    }
                }
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SimRadioButton(
            modifier = modifier,
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .offset(
                    y = TextRadioButtonYOffset,
                ),
        ) {
            Body5(
                text = text,
                color = SimTongColor.Gray700,
            )
        }
    }
}

@Preview
@Composable
fun PreviewRadioButton() {
    var checked by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }

    Column {
        SimRadioButton(
            checked = checked,
            onCheckedChange = { checked = !checked },
        )

        SimTextRadioButton(
            checked = checked2,
            onCheckedChange = { checked2 = !checked2 },
            text = "항목 1",
        )
    }
}
