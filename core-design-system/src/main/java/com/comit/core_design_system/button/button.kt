package com.comit.core_design_system.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon

/**
 * Defines the round of the SimTongButtons
 */
internal object ButtonDefaultRound {
    val Small = 3.dp
    val Medium = 5.dp
    val Large = 8.dp
}

enum class SimTongButtonColor(
    val backgroundColor: Color,
    val pressedBackgroundColor: Color,
    val disabledBackgroundColor: Color,
    val textColor: Color,
    val disabledTextColor: Color,
) {
    RED(
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
    ),

    GRAY(
        backgroundColor = SimTongColor.Gray700,
        pressedBackgroundColor = SimTongColor.Gray800,
        disabledBackgroundColor = SimTongColor.Gray400,
        textColor = SimTongColor.White,
        disabledTextColor = SimTongColor.White,
    ),

    WHITE(
        backgroundColor = SimTongColor.Gray50,
        pressedBackgroundColor = SimTongColor.Gray300,
        disabledBackgroundColor = SimTongColor.Gray200,
        textColor = SimTongColor.Gray300,
        disabledTextColor = SimTongColor.Gray300,
    ),
}

/**
 * default round in [SimTongSmallRoundButton]
 */
@Stable
private val SimTongSmallRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * SimTongSmallRoundButton을 구현합니다.
 * default round 는 [SimTongSmallRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongSmallRoundButton
 * @param round round in SimTongSmallRoundButton
 * @param text text in SimTongSmallRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongSmallRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = SimTongSmallRoundButtonDefaultRound,
    color: SimTongButtonColor = SimTongButtonColor.RED,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicSmallButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = color.backgroundColor,
        pressedBackgroundColor = color.pressedBackgroundColor,
        disabledBackgroundColor = color.disabledBackgroundColor,
        textColor = color.textColor,
        disabledTextColor = color.disabledTextColor,
        enabled = enabled,
    )
}

/**
 * default round in [SimTongBigRoundButton]
 */
@Stable
private val SimTongBigRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * SimTongBigRoundButton 구현합니다.
 * default round 는 [SimTongBigRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongBigRoundButton
 * @param round round in SimTongBigRoundButton
 * @param text text in SimTongBigRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongBigRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = SimTongBigRoundButtonDefaultRound,
    color: SimTongButtonColor = SimTongButtonColor.RED,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicBigButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = color.backgroundColor,
        pressedBackgroundColor = color.pressedBackgroundColor,
        disabledBackgroundColor = color.disabledBackgroundColor,
        textColor = color.textColor,
        disabledTextColor = color.disabledTextColor,
        enabled = enabled,
    )
}

/**
 * default round in [SimTongThinRoundButton]
 */
@Stable
private val SimTongThinRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * SimTongThinRoundButton 구현합니다.
 * default round 는 [SimTongThinRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongThinRoundButton
 * @param round round in SimTongThinRoundButton
 * @param text text in SimTongThinRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongThinRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = SimTongThinRoundButtonDefaultRound,
    color: SimTongButtonColor = SimTongButtonColor.RED,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicThinButton(
        modifier = modifier,
        text = text,
        shape = RoundedCornerShape(round),
        onClick = onClick,
        backgroundColor = color.backgroundColor,
        pressedBackgroundColor = color.pressedBackgroundColor,
        disabledBackgroundColor = color.disabledBackgroundColor,
        textColor = color.textColor,
        disabledTextColor = color.disabledTextColor,
        enabled = enabled,
    )
}

/**
 * default round in [SimTongSideBtn]
 */
@Stable
private val SimTongSideButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * SimTongSideButton 구현합니다.
 * 뷰 사이드에 위치하는 용도로 구현된 빨간색의 사이드 버튼입니다.
 * default round 는 [SimTongSideButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongSideButton
 * @param round round in SimTongSideButton
 * @param text text in SimTongSideButton
 * @param color color type in SimTongSideButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongSideBtn(
    modifier: Modifier = Modifier,
    round: Dp = SimTongSideButtonDefaultRound,
    text: String,
    color: SimTongButtonColor = SimTongButtonColor.RED,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicRoundSideButton(
        modifier = modifier,
        text = text,
        round = round,
        onClick = onClick,
        backgroundColor = color.backgroundColor,
        pressedBackgroundColor = color.pressedBackgroundColor,
        disabledBackgroundColor = color.disabledBackgroundColor,
        textColor = color.textColor,
        disabledTextColor = color.disabledTextColor,
        enabled = enabled,
    )
}

/**
 * SimTongIconButton 구현합니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongIconButton
 * @param painter [Painter] to go into button
 * @param contentDescription description of the SimTongIconButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    color: SimTongButtonColor = SimTongButtonColor.RED,
    contentDescription: String?,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicIconButton(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        onClick = onClick,
        backgroundColor = color.backgroundColor,
        pressedBackgroundColor = color.pressedBackgroundColor,
        disabledBackgroundColor = color.disabledBackgroundColor,
        enabled = enabled,
    )
}

@Preview
@Composable
fun ButtonPreview() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        SimTongSmallRoundButton(text = "NEXT") {
        }

        SimTongSmallRoundButton(text = "NEXT", enabled = false) {
        }

        SimTongBigRoundButton(text = "NEXT") {
        }

        SimTongBigRoundButton(text = "NEXT", enabled = false) {
        }

        SimTongThinRoundButton(text = "NEXT") {
        }

        SimTongThinRoundButton(text = "NEXT", enabled = false) {
        }

        SimTongSideBtn(text = "Text") {
        }

        SimTongSideBtn(text = "Text", enabled = false) {
        }

        SimTongIconButton(
            painter = painterResource(
                id = SimTongIcon.Next.drawableId,
            ),
            contentDescription = SimTongIcon.Next.contentDescription,
        ) {
        }

        SimTongIconButton(
            painter = painterResource(
                id = SimTongIcon.Next.drawableId,
            ),
            contentDescription = SimTongIcon.Next.contentDescription,
            enabled = false,
        ) {
        }

        SimTongSmallRoundButton(
            text = "NEXT",
            color = SimTongButtonColor.GRAY,
        ) {
        }

        SimTongSmallRoundButton(
            text = "NEXT",
            enabled = false,
            color = SimTongButtonColor.GRAY,
        ) {
        }

        SimTongBigRoundButton(
            text = "NEXT",
            color = SimTongButtonColor.GRAY,
        ) {
        }

        SimTongBigRoundButton(
            text = "NEXT",
            enabled = false,
            color = SimTongButtonColor.GRAY,
        ) {
        }

        SimTongSmallRoundButton(
            text = "NEXT",
            color = SimTongButtonColor.WHITE,
        ) {
        }

        SimTongSmallRoundButton(
            text = "NEXT",
            enabled = false,
            color = SimTongButtonColor.WHITE,
        ) {
        }

        SimTongBigRoundButton(
            text = "NEXT",
            color = SimTongButtonColor.WHITE,
        ) {
        }

        SimTongBigRoundButton(
            text = "NEXT",
            enabled = false,
            color = SimTongButtonColor.WHITE,
        ) {
        }
    }
}
