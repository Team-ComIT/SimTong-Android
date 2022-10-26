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
object ButtonDefaultRound {
    val Small = 3.dp
    val Medium = 5.dp
    val Large = 8.dp
}

/**
 * default round in [SmallRedRoundButton]
 */
@Stable
private val SmallRedRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * SmallRedRoundButton을 구현합니다.
 * default round 는 [SmallRedRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SmallRedRoundButton
 * @param round round in SmallRedRoundButton
 * @param text text in SmallRedRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SmallRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = SmallRedRoundButtonDefaultRound,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
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
        enabled = enabled,
    )
}

/**
 * default round in [BigRedRoundButton]
 */
@Stable
private val BigRedRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * BigRedRoundButton 구현합니다.
 * default round 는 [BigRedRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the BigRedRoundButton
 * @param round round in BigRedRoundButton
 * @param text text in BigRedRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun BigRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = BigRedRoundButtonDefaultRound,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
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
        enabled = enabled,
    )
}

/**
 * default round in [ThinRedRoundButton]
 */
@Stable
private val ThinRedRoundButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * ThinRedRoundButton 구현합니다.
 * default round 는 [ThinRedRoundButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the ThinRedRoundButton
 * @param round round in ThinRedRoundButton
 * @param text text in ThinRedRoundButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun ThinRedRoundButton(
    modifier: Modifier = Modifier,
    round: Dp = ThinRedRoundButtonDefaultRound,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
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
        enabled = enabled,
    )
}

enum class SideBtnColorType(
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
}

/**
 * default round in [SimTongSideBtn]
 */
@Stable
private val SimTongSideButtonDefaultRound = ButtonDefaultRound.Medium

/**
 * RedSideButton 구현합니다.
 * 뷰 사이드에 위치하는 용도로 구현된 빨간색의 사이드 버튼입니다.
 * default round 는 [SimTongSideButtonDefaultRound] 로 설정되어 있습니다.
 *
 * @param modifier [Modifier] to use to draw the SimTongSideButton
 * @param round round in SimTongSideButton
 * @param text text in SimTongSideButton
 * @param sideBtnColorType color type in SimTongSideBtn
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun SimTongSideBtn(
    modifier: Modifier = Modifier,
    round: Dp = SimTongSideButtonDefaultRound,
    text: String,
    sideBtnColorType: SideBtnColorType = SideBtnColorType.RED,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicRoundSideButton(
        modifier = modifier,
        text = text,
        round = round,
        onClick = onClick,
        backgroundColor = sideBtnColorType.backgroundColor,
        pressedBackgroundColor = sideBtnColorType.pressedBackgroundColor,
        disabledBackgroundColor = sideBtnColorType.disabledBackgroundColor,
        textColor = sideBtnColorType.textColor,
        disabledTextColor = sideBtnColorType.disabledTextColor,
        enabled = enabled,
    )
}

/**
 * RedIconButton 구현합니다.
 *
 * @param modifier [Modifier] to use to draw the RedIconButton
 * @param painter [Painter] to go into button
 * @param contentDescription description of the BasicIconButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 */
@Composable
fun RedIconButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String?,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    BasicIconButton(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,
        onClick = onClick,
        backgroundColor = SimTongColor.MainColor,
        pressedBackgroundColor = SimTongColor.MainColor600,
        disabledBackgroundColor = SimTongColor.MainColor200,
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

        SimTongSideBtn(text = "Text") {
        }

        SimTongSideBtn(text = "Text", enabled = false) {
        }

        RedIconButton(
            painter = painterResource(
                id = SimTongIcon.Next.drawableId,
            ),
            contentDescription = SimTongIcon.Next.contentDescription,
        ) {
        }

        RedIconButton(
            painter = painterResource(
                id = SimTongIcon.Next.drawableId,
            ),
            contentDescription = SimTongIcon.Next.contentDescription,
            enabled = false,
        ) {
        }
    }
}
