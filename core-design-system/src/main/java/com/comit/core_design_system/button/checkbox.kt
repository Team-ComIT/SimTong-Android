package com.comit.core_design_system.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.util.runIf

@Composable
fun BasicCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    selectedColor: Color,
    unSelectedColor: Color,
    disabledSelectedColor: Color,
    disabledUnSelectedColor: Color,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val backgroundColor: Color by animateColorAsState(
        if (enabled) selectedColor else disabledSelectedColor
    )

    val borderColor: Color by animateColorAsState(
        if (enabled) unSelectedColor else disabledUnSelectedColor
    )

    Box(
        modifier = modifier
            .background(
                color = if (checked) backgroundColor else Color.Transparent,
                shape = shape,
            )
            .runIf(!checked) {
                composed {
                    border(
                        width = 2.dp,
                        color = borderColor,
                    )
                }
            }
            .runIf(enabled) {
                composed {
                    simClickable(
                        rippleEnabled = enabled,
                        rippleColor = null,
                    ) {
                        onCheckedChange(!checked)
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            content()
        }
    }
}

@Composable
fun BasicIconCheckBox(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    selectedColor: Color,
    unSelectedColor: Color,
    disabledSelectedColor: Color,
    disabledUnSelectedColor: Color,
    enabled: Boolean = true,
    icon: SimTongIcon = SimTongIcon.Check,
) {
    BasicCheckBox(
        modifier = modifier,
        shape = shape,
        checked = checked,
        onCheckedChange = onCheckedChange,
        selectedColor = selectedColor,
        unSelectedColor = unSelectedColor,
        disabledSelectedColor = disabledSelectedColor,
        disabledUnSelectedColor = disabledUnSelectedColor,
        enabled = enabled,
    ) {
        Image(
            painter = painterResource(
                id = icon.drawableId,
            ),
            contentDescription = null,
        )
    }
}

@Composable
fun BasicRoundIconCheckBox(
    modifier: Modifier = Modifier,
    round: Dp = 0.dp,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    selectedColor: Color,
    unSelectedColor: Color,
    disabledSelectedColor: Color,
    disabledUnSelectedColor: Color,
    enabled: Boolean = true,
    icon: SimTongIcon = SimTongIcon.Check,
) {
    BasicIconCheckBox(
        modifier = modifier,
        shape = RoundedCornerShape(round),
        checked = checked,
        onCheckedChange = onCheckedChange,
        selectedColor = selectedColor,
        unSelectedColor = unSelectedColor,
        disabledSelectedColor = disabledSelectedColor,
        disabledUnSelectedColor = disabledUnSelectedColor,
        enabled = enabled,
        icon = icon,
    )
}

@Stable
private val DefaultCheckBoxRound = 2.dp

@Stable
private val DefaultCheckBoxSize = 24.dp

@Composable
fun SimCheckBox(
    modifier: Modifier = Modifier,
    round: Dp = DefaultCheckBoxRound,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    enabled: Boolean = true,
) {
    BasicRoundIconCheckBox(
        modifier = modifier.size(DefaultCheckBoxSize),
        round = round,
        checked = checked,
        onCheckedChange = onCheckedChange,
        selectedColor = SimTongColor.MainColor,
        unSelectedColor = SimTongColor.Gray500,
        disabledSelectedColor = SimTongColor.Gray300,
        disabledUnSelectedColor = SimTongColor.Gray200,
        enabled = enabled,
        icon = SimTongIcon.White_CheckBox,
    )
}

private val TextCheckBoxYOffset: Dp = (-1.2).dp

@Composable
fun SimTextCheckBox(
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
        SimCheckBox(
            modifier = modifier,
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
        )

        Spacer(modifier = Modifier.width(15.dp))

        Box(
            modifier = Modifier
                .offset(
                    y = TextCheckBoxYOffset,
                ),
        ) {
            Body4(
                text = text,
                color = SimTongColor.Gray800,
            )
        }
    }
}

@Preview
@Composable
fun PreviewCheckBox() {
    var checked by remember { mutableStateOf(false) }
    var checked2 by remember { mutableStateOf(false) }

    Column {
        SimCheckBox(
            checked = checked,
            onCheckedChange = { checked = !checked },
        )

        SimTextCheckBox(
            checked = checked2,
            onCheckedChange = { checked2 = !checked2 },
            text = "체크박스 테스트입니다.",
        )
    }
}
