package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon

@Composable
fun TextHeart(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    textColor: Color,
    click: Boolean = false,
    onClick: () -> Unit = {},
    heartModifier: Modifier = Modifier
) {

    val checkClickWatcher = rememberSaveable { mutableStateOf(click) }
    val textColorWatcher: Color = if (checkClickWatcher.value) SimTongColor.MainColor else textColor

    Row(
        modifier = Modifier
            .clickable {
                onClick()
                checkClickWatcher.value = !checkClickWatcher.value
            }
    ) {
        Image(
            painter = painterResource(
                id = if (checkClickWatcher.value) SimTongIcon.Heart_On.drawableId
                else SimTongIcon.Gray_Heart_Off.drawableId,
            ),
            contentDescription = if (checkClickWatcher.value) SimTongIcon.Heart_On.contentDescription
            else SimTongIcon.Gray_Heart_Off.contentDescription,
            modifier = heartModifier
        )

        Text(text = text, style = textStyle, color = textColorWatcher, modifier = modifier)
    }
}

// TODO ("modifier argument 2개 function naming 개선 필요")
@Composable
fun TextHeart2(
    modifier: Modifier = Modifier,
    text: Int,
    textStyle: TextStyle,
    textModifier: Modifier = Modifier,
    textColor: Color,
    heartModifier: Modifier = Modifier,
    click: Boolean = false,
    onClick: () -> Unit = {}
) {
    val checkClickWatcher = rememberSaveable { mutableStateOf(click) }
    val textColorWatcher: Color = if (checkClickWatcher.value) SimTongColor.MainColor else textColor
    val textWatcher = rememberSaveable { mutableStateOf(text) }

    Row(
        modifier = modifier
            .clickable {
                onClick()
                checkClickWatcher.value = !checkClickWatcher.value
                if (checkClickWatcher.value) {
                    textWatcher.value++
                } else {
                    textWatcher.value--
                }
            }
    ) {
        Image(
            painter = painterResource(
                id = if (checkClickWatcher.value) SimTongIcon.Heart_On.drawableId
                else SimTongIcon.Heart_Off.drawableId,
            ),
            contentDescription = stringResource(id = R.string.description_ic_heart),
            modifier = heartModifier
        )

        Text(
            text = textWatcher.value.toString(),
            style = textStyle,
            color = textColorWatcher,
            modifier = textModifier
        )
    }
}
