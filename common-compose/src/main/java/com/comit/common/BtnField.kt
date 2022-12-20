@file:NoLiveLiterals

package com.comit.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NoLiveLiterals
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.Error

@Stable
private val TextFieldHeight: Dp = 44.dp

@Stable
private val TextFieldMessagePadding = PaddingValues(start = 3.dp, top = 6.dp)

@Stable
private val BasicTextFieldStartPadding = 14.dp

@Stable
private val DefaultTextFieldRound: Dp = 5.dp

@Composable
fun SimTongBtnField(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    value: String,
    backgroundColor: Color = Color.White,
    title: String? = null,
    hint: String? = null,
    hintBackgroundColor: Color? = SimTongColor.OtherColor.GrayEE,
    round: Dp = DefaultTextFieldRound,
    error: String? = null,
    description: String? = null
) {
    val borderColor: Color =
        if (error == null) SimTongColor.Gray100 else SimTongColor.Error

    val hintBgColor: Color =
        hintBackgroundColor ?: Color.Transparent

    val bgColor: Color =
        if (value.isEmpty() && hint != null) hintBgColor else backgroundColor

    Column(
        modifier = modifier
            .simClickable(
                rippleEnabled = false,
                onClick = onClick,
            )
    ) {
        if (!title.isNullOrEmpty()) {
            Body8(
                text = title,
                color = SimTongColor.Gray400,
            )

            Spacer(modifier = Modifier.height(8.dp))
        }

        Box(
            modifier = Modifier
                .height(TextFieldHeight)
                .wrapContentHeight(Alignment.CenterVertically)
                .background(
                    color = bgColor,
                    shape = RoundedCornerShape(round),
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(round)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SimTongBasicBtnField(
                    value = value,
                    hint = hint,
                )
            }
        }

        if (!error.isNullOrEmpty()) {
            Error(
                text = error,
                modifier = Modifier.padding(TextFieldMessagePadding)
            )
        }

        if (description != null) {
            Body8(
                text = description,
                modifier = Modifier.padding(TextFieldMessagePadding),
                color = SimTongColor.Gray700
            )
        }
    }
}

@Composable
private fun SimTongBasicBtnField(
    value: String,
    hint: String? = null,
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = BasicTextFieldStartPadding)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (value.isEmpty() && hint != null) {
                Body6(
                    text = hint,
                    color = SimTongColor.Gray400,
                )
            } else {
                Body6(text = value)
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
