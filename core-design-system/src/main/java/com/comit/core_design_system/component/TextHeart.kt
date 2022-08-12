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
    textModifier: Modifier = Modifier,
    textColor: Color,
    heartModifier: Modifier,
    checkClick: Boolean = false,
    onClick: () -> Unit
) {
    val checkClick = rememberSaveable { mutableStateOf(checkClick) }
    val textColor: Color = if (checkClick.value) SimTongColor.MainColor else textColor

    Row(
        modifier = Modifier
            .clickable {
                onClick()
                checkClick.value = !checkClick.value
            }
    ) {
        Image(
            painter = painterResource(
                id = SimTongIcons.Heart(checkClick.value)
            ),
            contentDescription = "",
            modifier = heartModifier
        )

        Text(text = text, style = textStyle, color = textColor, modifier = textModifier)
    }
}

@Composable
fun TextHeart(
    text: Int,
    textStyle: TextStyle,
    textModifier: Modifier = Modifier,
    textColor: Color,
    heartModifier: Modifier,
    checkClick: Boolean = false,
    onClick: () -> Unit
) {
    val checkClick = rememberSaveable { mutableStateOf(checkClick) }
    val textColor: Color = if (checkClick.value) SimTongColor.MainColor else textColor
    val text = rememberSaveable { mutableStateOf(text) }

    Row(
        modifier = Modifier
            .clickable {
                onClick()
                checkClick.value = !checkClick.value
                if (checkClick.value) {
                    text.value++
                } else {
                    text.value--
                }
            }
    ) {
        Image(
            painter = painterResource(
                id = SimTongIcons.Heart(checkClick.value)
            ),
            contentDescription = "",
            modifier = heartModifier
        )

        Text(text = text.toString(), style = textStyle, color = textColor, modifier = textModifier)
    }
}
