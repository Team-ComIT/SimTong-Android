package com.comit.core_design_system.component

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.theme.Body4
import com.comit.core_design_system.theme.SimTongColor

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BasicCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    checkBoxSize: Dp = 20.dp,
    borderColor: Color = SimTongColor.MainColor,
    disableBorderColor: Color = SimTongColor.Gray400,
    backgroundColor: Color = SimTongColor.MainColor,
) {

    val interactionSources = remember { MutableInteractionSource() }

    val borderColor = if (checked) borderColor else disableBorderColor

    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(checkBoxSize)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = borderColor
            )
            .clickable(
                interactionSource = interactionSources,
                indication = null
            ) {
                if (onCheckedChange != null) {
                    onCheckedChange(!checked)
                }
            }
    ) {

        AnimatedVisibility(
            visible = checked,
            enter = scaleIn(tween(50)),
            exit = scaleOut(tween(50)),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
            )
        }
    }
}

@Composable
fun TextCheckBox(
    text: String,
    textColor: Color = SimTongColor.Gray800,
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    checkBoxSize: Dp = 20.dp,
    borderColor: Color = SimTongColor.MainColor,
    disableBorderColor: Color = SimTongColor.Gray400,
    backgroundColor: Color = SimTongColor.MainColor,
) {
    val interactionSources = remember { MutableInteractionSource() }

    Row(
        modifier = modifier.clickable(
            interactionSource = interactionSources,
            indication = null
        ) {
            if (onCheckedChange != null) {
                onCheckedChange(!checked)
            }
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicCheckBox(
            modifier = modifier,
            checked = checked,
            onCheckedChange = onCheckedChange,
            checkBoxSize = checkBoxSize,
            borderColor = borderColor,
            disableBorderColor = disableBorderColor,
            backgroundColor = backgroundColor
        )

        Spacer(modifier = Modifier.width(8.dp))

        Body4(text = text, color = textColor)
    }
}


@Preview
@Composable
fun PreviewCheckBox() {
    var value1 by remember { mutableStateOf(false) }
    var value2 by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BasicCheckBox(checked = value1, onCheckedChange = { value1 = it })

        TextCheckBox(text = "설명설명", checked = value2, onCheckedChange = { value2 = it })
    }

}