@file:NoLiveLiterals

package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.button.BasicRoundSideButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.Error
import com.comit.core_design_system.typography.SimTongTypography
import com.comit.core_design_system.util.runIf

@Stable
private val TextFieldEnabledFraction: Float = 0.75f

@Stable
private val TextFieldDisabledFraction: Float = 0.9f

@Stable
private val TextFieldHeight: Dp = 44.dp

@Stable
private val TextFieldMessagePadding = PaddingValues(start = 3.dp, top = 6.dp)

@Stable
private val BasicTextFieldStartPadding = 14.dp

@Stable
private val DefaultTextFieldRound: Dp = 5.dp

/**
 * SimTong Design System TextField [SimTongTextField].
 * The current implementation method is not abstracted from [SimTongTextField].
 * Therefore, it is necessary to create and abstract SimTongBasicTextField later.
 *
 * @param modifier [Modifier] to use to draw the SimTongTextField
 * @param value text to display
 * @param onValueChange Callback to be invoked when new text is entered
 * @param backgroundColor backgroundColor in SimTongTextField
 * @param onClick Callback to be invoked when a textField is clicked
 * @param hint hint message in SimTongTextField
 * @param hintBackgroundColor backgroundColor with Hint
 * @param enabledSideBtn whether side button is used
 * @param sideBtnText text of side button
 * @param sideBtnTextColor text of side button's text
 * @param sideBtnBackgroundColor backgroundColor of side button
 * @param sideBtnPressedBackgroundColor backgroundColor when side button is pressed
 * @param sideBtnDisabledTextColor color of side button's text when side button is disabled
 * @param round radius of SimTongTextField
 * @param onSideBtnClick Callback to be invoked when a side button is clicked
 * @param error message when an error occurs
 * @param isPassword Whether to enable password functionality
 * @param keyboardType keyboardType in SimTongTextField
 * @param imeAction ImeAction in SimTongTextField
 * @param description description in SimTongTextField
 */
@Composable
fun SimTongTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    backgroundColor: Color = Color.White,
    onClick: (() -> Unit)? = null,
    hint: String? = null,
    hintBackgroundColor: Color? = SimTongColor.OtherColor.GrayEE,
    enabledSideBtn: Boolean = false,
    sideBtnText: String? = null,
    sideBtnTextColor: Color = SimTongColor.White,
    sideBtnBackgroundColor: Color = SimTongColor.MainColor,
    sideBtnPressedBackgroundColor: Color = SimTongColor.MainColor600,
    sideBtnDisabledBackgroundColor: Color = SimTongColor.MainColor200,
    sideBtnDisabledTextColor: Color = SimTongColor.White,
    round: Dp = DefaultTextFieldRound,
    onSideBtnClick: (() -> Unit)? = null,
    error: String? = null,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    description: String? = null
) {
    val borderColor: Color =
        if (error == null) SimTongColor.Gray200 else SimTongColor.Error

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    val hintBgColor: Color =
        hintBackgroundColor ?: Color.Transparent

    val textFieldFraction =
        if (enabledSideBtn) TextFieldEnabledFraction
        else TextFieldDisabledFraction

    val bgColor: Color =
        if (value.isEmpty() && hint != null) hintBgColor else backgroundColor

    Column {
        Box(
            modifier = modifier
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
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth(textFieldFraction)
                        .padding(start = BasicTextFieldStartPadding),
                    value = value,
                    onValueChange = onValueChange,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction
                    ),
                    visualTransformation =
                    if (!passwordVisible && isPassword)
                        PasswordVisualTransformation()
                    else VisualTransformation.None,
                    maxLines = 1,
                    textStyle = SimTongTypography.body6,
                    decorationBox = { innerTextField ->
                        if (value.isEmpty() && hint != null) {
                            Body6(text = hint, color = SimTongColor.Gray400)
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

                if (value.isNotEmpty()) {
                    Image(
                        modifier = Modifier
                            .simClickable(
                                rippleEnabled = false,
                            ) {
                                onValueChange("")
                            },
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "close icon",
                    )
                }

                if (isPassword) {
                    Image(
                        modifier = Modifier
                            .simClickable(
                                rippleEnabled = false,
                            ) {
                                passwordVisible = !passwordVisible
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

@Preview
@Composable
fun PreviewSimTongTextField() {
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

        SimTongTextField(
            value = value7 ?: "",
            onValueChange = { value7 = it },
            error = ""
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
