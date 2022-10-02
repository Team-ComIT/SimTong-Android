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
import androidx.compose.ui.text.TextStyle
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.SimTongColor

@Composable
fun TextHeart(
    text: String,
    textStyle: TextStyle,
    textColor: Color,
    click: Boolean = false,
    isGray: Boolean,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier,
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
                id = SimTongIcons.Heart(checkClickWatcher.value, isGray)
            ),
            contentDescription = "heart image",
            modifier = heartModifier
        )

        Text(text = text, style = textStyle, color = textColorWatcher, modifier = modifier)
    }
}

@Composable
fun TextHeart(
    text: Int,
    textStyle: TextStyle,
    textModifier: Modifier = Modifier,
    textColor: Color,
    modifier: Modifier = Modifier,
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
                id = SimTongIcons.Heart(checkClickWatcher.value)
            ),
            contentDescription = "heart image",
            modifier = heartModifier
        )

        Text(text = textWatcher.value.toString(), style = textStyle, color = textColorWatcher, modifier = textModifier)
    }
}
