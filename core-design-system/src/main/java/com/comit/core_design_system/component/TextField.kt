package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.*

@Composable
fun SimTongTextField(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    value: String,
    enabledSideBtn: Boolean = false,
    sideBtnText: String? = null,
    sideBtnBackgroundColor: Color = SimTongColor.MainColor,
    sideBtnPressedBackgroundColor: Color = SimTongColor.MainColor600,
    sideBtnDisabledBackgroundColor: Color = SimTongColor.MainColor200,
    sideBtnTextColor: Color = SimTongColor.White,
    round: Dp = 5.dp,
    sideBtnDisabledTextColor: Color = SimTongColor.White,
    onSideBtnClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    error: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    onClick: (() -> Unit)? = null,
    hint: String? = null,
    description: String? = null
) {
    val interactionSource = remember { MutableInteractionSource() }

    val borderColor: Color = if (error == null) SimTongColor.Gray300 else SimTongColor.Error

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val textFieldFraction = if (enabledSideBtn) 0.75f else 0.9f

    Column {
        Box(
            modifier = modifier
                .height(44.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(round),
                )
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(round)
                )
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    if (onClick != null) {
                        onClick()
                    }
                }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth(textFieldFraction)
                        .padding(start = 14.dp),
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction
                    ),
                    visualTransformation = if (!passwordVisible && isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                    maxLines = 1,
                    textStyle = SimTongTypography.body6,
                    decorationBox = { innerTextField ->
                        if (value.isEmpty() && hint != null) {
                            Body6(text = hint, color = SimTongColor.Gray900)
                        }

                        innerTextField()
                    },
                )

                if (enabledSideBtn) {
                    BasicRoundSideButton(
                        text = sideBtnText ?: "",
                        backgroundColor = sideBtnBackgroundColor,
                        pressedBackgroundColor = sideBtnPressedBackgroundColor,
                        disabledBackgroundColor = sideBtnDisabledBackgroundColor,
                        textColor = sideBtnTextColor,
                        disabledTextColor = sideBtnDisabledTextColor,
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentWidth(Alignment.End),
                        round = round
                    ) {
                        if (onSideBtnClick != null) {
                            onSideBtnClick()
                        }
                    }
                }

                if (isPassword) {
                    Image(
                        modifier = Modifier
                            .clickable(
                                interactionSource = interactionSource,
                                indication = null
                            ) { passwordVisible = !passwordVisible },
                        painter = painterResource(id = SimTongIcons.Password(passwordVisible)),
                        contentDescription = stringResource(if (passwordVisible) R.string.descriptiom_ic_password_visible else R.string.descriptiom_ic_password_invisible),
                        alpha = if (value.isNotEmpty()) 1f else 0f
                    )
                }
            }
        }

        if (error != null) {
            Error(
                text = error,
                modifier = Modifier.padding(start = 3.dp, top = 6.dp)
            )
        }

        if (description != null) {
            Body8(
                text = description,
                modifier = Modifier.padding(start = 3.dp, top = 6.dp),
                color = SimTongColor.Gray700
            )
        }
    }
}

@Preview
@Composable
fun PreviewMoizaTextField() {
    var value by remember { mutableStateOf<String?>(null) }
    var value2 by remember { mutableStateOf<String?>(null) }
    var value3 by remember { mutableStateOf<String?>(null) }
    var value4 by remember { mutableStateOf<String?>(null) }
    var value5 by remember { mutableStateOf<String?>(null) }
    var value6 by remember { mutableStateOf<String?>(null) }
    var value7 by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // default text field
        SimTongTextField(
            value = value ?: "",
            onValueChange = { value = it }
        )

        // password text field
        SimTongTextField(
            value = value2 ?: "",
            onValueChange = { value2 = it },
            isPassword = true
        )

        // error text field
        SimTongTextField(
            value = value3 ?: "",
            onValueChange = { value3 = it },
            error = "특수문자는 사용할 수 없습니다!"
        )

        // description text field
        SimTongTextField(
            value = value4 ?: "",
            onValueChange = { value4 = it },
            description = "비밀번호는 4자리 이상 입력해주세요.",
            hint = "비밀번호"
        )

        // side btn text field
        SimTongTextField(
            value = value5 ?: "",
            onValueChange = { value5 = it },
            enabledSideBtn = true,
            sideBtnText = "인증"
        )

        // custom background
        SimTongTextField(
            value = value6 ?: "",
            onValueChange = { value6 = it },
            backgroundColor = SimTongColor.Gray200
        )

        // custom background & side btn
        SimTongTextField(
            value = value7 ?: "",
            onValueChange = { value7 = it },
            backgroundColor = SimTongColor.Gray200,
            sideBtnText = "인증",
            sideBtnBackgroundColor = SimTongColor.Gray600,
            enabledSideBtn = true,
            sideBtnDisabledBackgroundColor = SimTongColor.Gray600,
            sideBtnPressedBackgroundColor = SimTongColor.Gray700,
        )
    }
}