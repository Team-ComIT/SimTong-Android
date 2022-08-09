package com.comit.core_design_system.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.SimTongColor

@Composable
fun SmallRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = 5.dp,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicSmallButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
        enabled = enabled
    )
}

@Composable
fun BigRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = 5.dp,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicBigButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
        enabled = enabled
    )
}

@Composable
fun ThinRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = 5.dp,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicThinButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
        enabled = enabled
    )
}

@Composable
fun RedSideButton(
    modifier: Modifier = Modifier,
    round: Dp = 5.dp,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundSideButton(
        modifier = modifier,
        text = text,
        round = round,
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
        enabled = enabled
    )
}

@Composable
fun GraySideButton(
    modifier: Modifier = Modifier,
    round: Dp = 5.dp,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicRoundSideButton(
        modifier = modifier,
        text = text,
        round = round,
        onClick = onClick,
        backgroundColor = SimTongColor.Gray700,
        pressedBackgroundColor = SimTongColor.Gray800,
        disabledBackgroundColor = SimTongColor.Gray400,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
        enabled = enabled
    )
}


@Composable
fun RedIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    BasicIconButton(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        enabled = enabled
    )
}

@Preview
@Composable
fun ButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SmallRedRoundButton(text = "NEXT") {

        }

        SmallRedRoundButton(text = "NEXT", enabled = false) {

        }

        BigRedRoundButton(text = "NEXT") {

        }

        BigRedRoundButton(text = "NEXT", enabled = false) {

        }

        ThinRedRoundButton(text = "NEXT") {

        }

        ThinRedRoundButton(text = "NEXT", enabled = false) {

        }

        RedSideButton(text = "Text") {

        }

        RedSideButton(text = "Text", enabled = false) {

        }

        RedIconButton(painter = painterResource(id = SimTongIcons.Next), contentDescription = null) {

        }

        RedIconButton(painter = painterResource(id = SimTongIcons.Next), contentDescription = null, enabled = false) {

        }
    }
}