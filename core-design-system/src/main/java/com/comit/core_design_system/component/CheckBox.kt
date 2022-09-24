package com.comit.core_design_system.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.theme.Body4
import com.comit.core_design_system.theme.SimTongColor

/**
 * Defines the size of the SimTongCheckBox
 */
object CheckBoxSize {
    val Small = 15.dp
    val Medium = 20.dp
    val Large = 25.dp
}

@Stable
private val BasicCheckBoxScaleInTweenMillis: Int = 50

@Stable
private val BasicCheckBoxScaleOutTweenMillis: Int = 50

@Stable
private val BasicCheckBoxDefaultSize = CheckBoxSize.Medium

@Stable
private val BasicCheckBoxContentPadding = 4.dp

@Stable
private val BasicCheckBoxBorderWidth = 1.dp

/**
 * Implement [BasicCheckBox], the lowest component of the CheckBox.
 *
 * @param modifier [Modifier] to use to draw the SimTongTextField,
 * @param checked check status
 * @param onCheckedChange Callback to be invoked if checked
 * @param checkBoxSize checkBoxSize in BasicCheckBox,
 * @param borderColor color in BasicCheckBox's border
 * @param disableBorderColor disabled color in BasicCheckBox's border
 * @param backgroundColor backgroundColor in BasicCheckBox
 */

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BasicCheckBox(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    checkBoxSize: Dp = BasicCheckBoxDefaultSize,
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
                width = BasicCheckBoxBorderWidth,
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
            enter = scaleIn(tween(BasicCheckBoxScaleInTweenMillis)),
            exit = scaleOut(tween(BasicCheckBoxScaleOutTweenMillis)),
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .padding(BasicCheckBoxContentPadding)
                    .clip(CircleShape)
                    .background(backgroundColor)
            )
        }
    }
}

@Stable
private val TextCheckBoxDefaultSize = CheckBoxSize.Medium

@Stable
private val TextCheckBoxSpacerWidth = 8.dp

/**
 * SimTongDesignSystem의 [TextCheckBox] 를 구현합니다.
 * [BasicCheckBox]와 [Body4]가 혼합된 형식의 컴포넌트입니다.
 *
 * @param text text in TextCheckBox
 * @param textColor color of TextCheckBox's text
 */
@Composable
fun TextCheckBox(
    modifier: Modifier = Modifier,
    text: String,
    textColor: Color = SimTongColor.Gray800,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    checkBoxSize: Dp = TextCheckBoxDefaultSize,
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

        Spacer(modifier = Modifier.width(TextCheckBoxSpacerWidth))

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
