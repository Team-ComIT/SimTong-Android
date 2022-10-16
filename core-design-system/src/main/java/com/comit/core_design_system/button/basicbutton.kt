package com.comit.core_design_system.button

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.util.runIf

/**
 * The lowest component of SimTongButtons
 *
 * @param modifier [Modifier] to use to draw the SimTongTextField
 * @param shape shape in BasicButton
 * @param enabled activation status of button
 * @param onClick Callback to be invoked when a button is clicked
 * @param backgroundColor backgroundColor in BasicButton
 * @param pressedBackgroundColor backgroundColor when side button is pressed
 * @param disabledBackgroundColor color of side button's text when side button is disabled
 * @param content BasicButton's internal composable
 */
@Composable
fun BasicButton(
    modifier: Modifier = Modifier,
    shape: Shape = RectangleShape,
    enabled: Boolean = true,
    onClick: () -> Unit,
    backgroundColor: Color,
    pressedBackgroundColor: Color,
    disabledBackgroundColor: Color,
    content: @Composable (isPressed: Boolean) -> Unit,
) {

    val interactionSource = remember { MutableInteractionSource() }

    val isPressed = interactionSource.collectIsPressedAsState()

    val btnColor =
        if (!enabled) disabledBackgroundColor else if (isPressed.value) pressedBackgroundColor else backgroundColor

    Box(
        modifier = modifier
            .background(
                color = btnColor,
                shape = shape,
            )
            .runIf(enabled) {
                composed {
                    simClickable(
                        rippleEnabled = true,
                        rippleColor = pressedBackgroundColor,
                        onClick = onClick,
                    )
                }
            },
        contentAlignment = Alignment.Center
    ) {
        content(isPressed.value)
    }
}

/**
 * Height value in [BasicBigButton]
 */
@Stable
private val BasicBigButtonHeight = 42.dp

/**
 * Basic 버튼의 [BasicBigButton] 을 구현합니다.
 * 다른 Basic 버튼들과 동일 버튼이며
 * 차이점은 버튼 사이즈가 큽니다. 이 때문에
 * [fillMaxWidth] 로 처리해주었습니다.
 */
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
    disabledTextColor: Color,
) {
    val contentColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .height(BasicBigButtonHeight),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body3(
            text = text,
            color = contentColor,
        )
    }
}

/**
 * Horizontal padding in [BasicSmallButton]
 */
@Stable
private val BasicSmallButtonHorizontalPadding = 40.dp

/**
 * Height value in [BasicSmallButton]
 */
@Stable
private val BasicSmallButtonHeight = 36.dp

/**
 * Basic 버튼의 [BasicSmallButton] 을 구현합니다.
 * 다른 Basic 버튼들과 동일 버튼이며
 * 차이점은 버튼 사이즈를 작게 구현하였습니다.
 * 따라서 horizontal padding 값을 [BasicSmallButtonHorizontalPadding] 만큼 주었습니다.
 */
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
    disabledTextColor: Color,
) {
    val contentColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BasicSmallButtonHorizontalPadding)
            .height(BasicSmallButtonHeight),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body3(
            text = text,
            color = contentColor,
        )
    }
}

/**
 * Horizontal padding in [BasicSmallButton]
 */
@Stable
private val BasicThinButtonHorizontalPadding = 30.dp

/**
 * Height value in [BasicSmallButton]
 */
@Stable
private val BasicThinButtonHeight = 30.dp

/**
 * Basic 버튼의 [BasicThinButton] 을 구현합니다.
 * 다른 Basic 버튼들과 동일 버튼이며
 * 차이점은 버튼 사이즈를 얇게 구현하였습니다.
 *
 * 따라서 horizontal padding 을 [BasicThinButtonHorizontalPadding] 만큼
 * button height 를 [BasicThinButtonHeight] 만큼 설정하였습니다.
 */
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
    disabledTextColor: Color,
) {
    val contentColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = BasicThinButtonHorizontalPadding)
            .height(BasicThinButtonHeight),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body5(
            text = text,
            color = contentColor,
        )
    }
}

/**
 * Width value in [BasicSmallButton]
 */
@Stable
private val BasicSideButtonWidth = 69.dp

/**
 * Height value in [BasicSmallButton]
 */
@Stable
private val BasicSideButtonHeight = 40.dp

/**
 * Basic 버튼의 [BasicSideButton] 을 구현합니다.
 * TextField 등에 사이드 버튼으로 사용될 용도로 제작되어
 * 작은 사이즈로 구현하여 사이즈가 정적으로 박힌 버튼입니다.
 *
 * Width - [BasicSideButtonWidth]
 * Height - [BasicSideButtonHeight]
 */
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
    val contentColor = if (enabled) textColor else disabledTextColor

    BasicButton(
        modifier = modifier
            .width(BasicSideButtonWidth)
            .height(BasicSideButtonHeight),
        shape = shape,
        enabled = enabled,
        onClick = onClick,
        backgroundColor = backgroundColor,
        pressedBackgroundColor = pressedBackgroundColor,
        disabledBackgroundColor = disabledBackgroundColor,
    ) {
        Body10(
            text = text,
            color = contentColor,
        )
    }
}

/**
 * Basic 버튼의 [BasicRoundSideButton] 을 구현합니다.
 * 다른 버튼과 기능을 동일하지만 둥근 모양을 가진 버튼입니다.
 *
 * @param round round in BasicRoundSideButton
 */
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
    onClick: () -> Unit,
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
        disabledTextColor = disabledTextColor,
    )
}

/**
 * Basic 버튼의 [BasicIconButton] 을 구현합니다.
 * 둥근 모양에 아이콘이 있는 버튼입니다.
 *
 * @param painter [Painter] to go into button
 * @param contentDescription description of the BasicIconButton
 * @param shpae shape of BasicIconButton
 */
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
        Image(
            painter = painter,
            contentDescription = contentDescription,
        )
    }
}
