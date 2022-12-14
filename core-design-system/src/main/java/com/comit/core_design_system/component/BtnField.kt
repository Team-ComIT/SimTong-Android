@file:NoLiveLiterals

package com.comit.core_design_system.component

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NoLiveLiterals
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.button.SimTongSideBtn
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.noRippleClickable
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.Error
import com.comit.core_design_system.util.runIf

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
    basicClick: (() -> Unit),
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.White,
    onClick: (() -> Unit)? = null,
    title: String? = null,
    hint: String? = null,
    hintBackgroundColor: Color? = SimTongColor.OtherColor.GrayEE,
    enabledSideBtn: Boolean = false,
    sideBtnText: String? = null,
    simTongButtonColor: SimTongButtonColor = SimTongButtonColor.RED,
    round: Dp = DefaultTextFieldRound,
    onSideBtnClick: (() -> Unit)? = null,
    error: String? = null,
    isPassword: Boolean = false,
    description: String? = null
) {
    val borderColor: Color =
        if (error == null) SimTongColor.Gray100 else SimTongColor.Error

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val hintBgColor: Color =
        hintBackgroundColor ?: Color.Transparent

    val bgColor: Color =
        if (value.isEmpty() && hint != null) hintBgColor else backgroundColor

    Column(
        modifier = modifier
            .noRippleClickable { basicClick() },
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
                .runIf(onClick != null) {
                    composed {
                        simClickable(
                            rippleEnabled = false
                        ) {
                            onClick!!
                        }
                    }
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                SimTongBasicBtnField(
                    value = value,
                    onValueChange = onValueChange,
                    hint = hint,
                    passwordVisible = passwordVisible,
                    onPasswordVisibleChanged = {
                        passwordVisible = it
                    },
                    enabledSideBtn = enabledSideBtn,
                    sideBtnText = sideBtnText,
                    round = round,
                    onSideBtnClick = onSideBtnClick,
                    simTongButtonColor = simTongButtonColor,
                    isPassword = isPassword,
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
    onValueChange: (String) -> Unit,
    hint: String? = null,
    passwordVisible: Boolean = false,
    onPasswordVisibleChanged: (Boolean) -> Unit,
    enabledSideBtn: Boolean = false,
    sideBtnText: String? = null,
    simTongButtonColor: SimTongButtonColor,
    round: Dp = DefaultTextFieldRound,
    onSideBtnClick: (() -> Unit)? = null,
    isPassword: Boolean = false,
) {
//    BasicTextField(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = BasicTextFieldStartPadding),
//        value = value,
//        onValueChange = onValueChange,
//        keyboardOptions = keyboardOptions,
//        visualTransformation =
//        if (!passwordVisible && isPassword)
//            PasswordVisualTransformation()
//        else VisualTransformation.None,
//        maxLines = 1,
//        textStyle = SimTongTypography.body6,
//        decorationBox = @Composable { innerTextField ->
//
//        },
//    )

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
            }

            Spacer(modifier = Modifier.weight(1f))

            if (value.isNotEmpty()) {
                Image(
                    modifier = Modifier
                        .simClickable(
                            rippleEnabled = false,
                        ) {
                            onValueChange("")
                        },
                    painter = painterResource(
                        id = SimTongIcon.Close.drawableId,
                    ),
                    contentDescription = SimTongIcon.Close.contentDescription,
                )
            }

            if (isPassword) {
                Image(
                    modifier = Modifier
                        .simClickable(
                            rippleEnabled = false,
                        ) {
                            onPasswordVisibleChanged(!passwordVisible)
                        },
                    painter = painterResource(
                        id = if (passwordVisible) SimTongIcon.Password_Visible.drawableId
                        else SimTongIcon.Password_InVisible.drawableId,
                    ),
                    contentDescription =
                    stringResource(
                        if (passwordVisible) R.string.descriptiom_ic_password_visible
                        else R.string.descriptiom_ic_password_invisible
                    ),
                    alpha = if (value.isNotEmpty()) 1f else 0f
                )
            }

            Spacer(modifier = Modifier.width(9.dp))

            if (enabledSideBtn) {

                SimTongSideBtn(
                    text = sideBtnText ?: "",
                    round = round,
                    color = simTongButtonColor,
                ) {
                    if (onSideBtnClick != null) {
                        onSideBtnClick()
                    }
                }
            }
        }
    }
}