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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
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
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.button.SimTongSideBtn
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.Error
import com.comit.core_design_system.typography.SimTongTypography
import com.comit.core_design_system.util.runIf

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
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
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
        modifier = modifier,
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
                SimTongBasicTextField(
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
                    keyboardOptions = KeyboardOptions(
                        keyboardType = keyboardType,
                        imeAction = imeAction,
                    ),
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
private fun SimTongBasicTextField(
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
    keyboardOptions: KeyboardOptions = KeyboardOptions(
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Default,
    ),
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = BasicTextFieldStartPadding),
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        visualTransformation =
        if (!passwordVisible && isPassword)
            PasswordVisualTransformation()
        else VisualTransformation.None,
        maxLines = 1,
        textStyle = SimTongTypography.body6,
        decorationBox = @Composable { innerTextField ->

            Box(
                contentAlignment = Alignment.CenterStart,
            ) {
                innerTextField()

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
        },
    )
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
            onValueChange = { value = it },
            error = null
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
            backgroundColor = SimTongColor.Gray100
        )

        // custom background & side btn
        SimTongTextField(
            value = value7 ?: "",
            onValueChange = { value7 = it },
            backgroundColor = SimTongColor.Gray100,
            sideBtnText = "인증",
            simTongButtonColor = SimTongButtonColor.GRAY,
            enabledSideBtn = true,
        )

        SimTongTextField(
            title = "사원번호",
            value = value7 ?: "",
            onValueChange = { value7 = it },
            error = ""
        )
    }
}
