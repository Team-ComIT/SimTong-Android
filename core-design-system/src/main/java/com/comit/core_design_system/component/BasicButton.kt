package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.theme.Body10
import com.comit.core_design_system.theme.Body3
import com.comit.core_design_system.theme.Body5

@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    content: @Composable (isPressed: Boolean) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed = interactionSource.collectIsPressedAsState()

    val btnColor =
        if (!enabled) disabledBackgroundColor else if (isPressed.value) pressedBackgroundColor else backgroundColor

    Box(
        modifier = modifier
            .background(
                color = btnColor,
                shape = shape
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
                enabled = enabled
            ),
        contentAlignment = Alignment.Center
    ) {
        content(isPressed.value)
    }
}

@Composable
fun BasicBigButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color
) {
    val textColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .height(42.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body3(text = text, color = textColor)
    }
}

@Composable
fun BasicSmallButton(
    text: String,
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color
) {
    val textColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp)
            .height(36.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body3(text = text, color = textColor)
    }
}

@Composable
fun BasicThinButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    shape: Shape = RectangleShape,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color
) {
    val textColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .height(30.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body5(text = text, color = textColor)
    }
}

@Composable
fun BasicSideButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = RectangleShape,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color,
    onClick: () -> Unit,
) {
    val textColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .width(69.dp)
            .height(40.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body10(text = text, color = textColor)
    }
}

@Composable
fun BasicRoundSideButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    round: Dp = 0.dp,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    textColor: Color,
    disabledTextColor: Color,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        bottomStart = 0.dp,
        topEnd = round,
        bottomEnd = round
    )

    BasicSideButton(
        text = text,
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
        shape = shape,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
        textColor = textColor,
        disabledTextColor = disabledTextColor
    )
}

@Composable
fun BasicIconButton(
    painter: Painter,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
    shape: Shape = CircleShape,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
) {

    BasicButton(
        modifier = modifier.size(40.dp),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor
    ) {
        Image(painter = painter, contentDescription = contentDescription)
    }
}
