package com.comit.feature_auth.screen.signUp

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.SimTongSimpleLayout
import com.comit.core_design_system.button.SimTextCheckBox
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.UnderlineBody9
import com.comit.feature_auth.R
import kotlinx.coroutines.launch
import kotlin.math.abs

/**
 * TextField의 Offset을 계산합니다.
 */
private fun textFieldOffset(
    step: SignUpStep.InputUserInfo,
    currentStep: SignUpStep.InputUserInfo,
): Dp {
    return (abs(0.coerceAtMost(step.offsetIdx - currentStep.offsetIdx) * TextFieldMargin)).dp
}

private val TextFieldEnterAnimation = fadeIn(tween(450))

private const val TextFieldMargin: Int = 8

/**
 * 약관 목록을 정의합니다.
 * 해당 약관 목록은 [SignUpNameScreen] 의 약관 동의 BottomSheet 에서 사용도비니다.
 */
private val agreedList: List<String> =
    listOf(
        "약관1",
        "약관2",
        "약관3",
        "약관4"
    )

/**
 * 회원가입 1단계 Screen
 * 유저의 기초적인 정보를 받습니다.
 *
 * @author limsaehyun
 * @since 2022.12.08
 * 약관동의가 빠짐에 따라 현재 비활성화 시켜두었습니다.
 * 하지만 추후에 사용될 수 있으므로 관련 코드를 남겨둡니다
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpNameScreen(
    toPrevious: () -> Unit,
    verificationEmployeeNumber: () -> Unit,
    name: String,
    onNameChanged: (String) -> Unit,
    employeeNumber: String,
    fieldErrEmployeeNumber: String?,
    onEmployeeNumberChanged: (String) -> Unit,
    email: String,
    fieldErrEmail: String?,
    onEmailChanged: (String) -> Unit,
    signUpNameStep: SignUpStep.InputUserInfo,
    navigatePage: (SignUpStep.InputUserInfo) -> Unit,
    sendVerifyCode: () -> Unit,
    toSignIn: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    val nextBtnClick = {
        when (signUpNameStep) {
            SignUpStep.InputUserInfo.NAME -> navigatePage(SignUpStep.InputUserInfo.EMPLOYEE_NUMBER)
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> {
                verificationEmployeeNumber()
            }
            SignUpStep.InputUserInfo.EMAIL -> {
                sendVerifyCode()
            }
        }
    }

    val backBtnClick = {
        when (signUpNameStep) {
            SignUpStep.InputUserInfo.EMAIL -> navigatePage(SignUpStep.InputUserInfo.EMPLOYEE_NUMBER)
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> navigatePage(SignUpStep.InputUserInfo.NAME)
            SignUpStep.InputUserInfo.NAME -> toPrevious()
        }
    }

    val nameOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.NAME,
            currentStep = signUpNameStep,
        )
    )
    val employeeNumberOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER,
            currentStep = signUpNameStep,
        )
    )
    val emailOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMAIL,
            currentStep = signUpNameStep,
        )
    )

    val agreedList = remember {
        agreedList.toMutableStateList()
    }

    val agreedChecked = remember {
        mutableStateListOf(
            elements = Array(
                size = agreedList.size,
                init = { false }
            ),
        )
    }

    val btnEnabled = when (signUpNameStep) {
        SignUpStep.InputUserInfo.NAME -> name.isNotEmpty()
        SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> employeeNumber.isNotEmpty()
        SignUpStep.InputUserInfo.EMAIL -> employeeNumber.isNotEmpty()
    }

    BackHandler {
        coroutineScope.launch {
            backBtnClick()
        }
    }

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                Body1(
                    text = stringResource(
                        id = R.string.sign_up_terms_agreed,
                    ),
                )

                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    modifier = Modifier
                        .padding(horizontal = 18.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    SimTextCheckBox(
                        text = stringResource(
                            id = R.string.sign_up_terms_agreed_all,
                        ),
                        checked = agreedChecked.all { it },
                        onCheckedChange = {
                            val checked = agreedChecked.all { it }
                            List(agreedList.size) { index ->
                                agreedChecked[index] = !checked
                            }
                        },
                    )

                    Divider(
                        thickness = 1.dp,
                        color = SimTongColor.Gray200,
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                    ) {
                        itemsIndexed(agreedList) { index, item ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                SimTextCheckBox(
                                    text = item,
                                    checked = agreedChecked[index],
                                    onCheckedChange = {
                                        agreedChecked[index] = !agreedChecked[index]
                                    },
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(47.dp))

                SimTongBigRoundButton(
                    text = stringResource(
                        id = R.string.next,
                    ),
                ) {
                    coroutineScope.launch {
                        bottomSheetState.hide()
                        nextBtnClick()
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        },
    ) {
        SimTongSimpleLayout(
            topAppBar = {
                BigHeader(
                    text = stringResource(id = R.string.sign_up),
                ) {
                    backBtnClick()
                }
            },
            content = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 20.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    AnimatedVisibility(
                        visible = signUpNameStep.offsetIdx >= SignUpStep.InputUserInfo.EMAIL.offsetIdx,
                        enter = TextFieldEnterAnimation
                    ) {
                        SimTongTextField(
                            modifier = Modifier.offset(y = emailOffset),
                            title = stringResource(
                                id = R.string.email,
                            ),
                            value = email,
                            onValueChange = {
                                onEmailChanged(it)
                            },
                            error = fieldErrEmail,
                            keyboardType = KeyboardType.Email,
                        )
                    }

                    AnimatedVisibility(
                        visible = signUpNameStep.offsetIdx >= SignUpStep.InputUserInfo.EMPLOYEE_NUMBER.offsetIdx,
                        enter = TextFieldEnterAnimation
                    ) {
                        SimTongTextField(
                            modifier = Modifier.offset(
                                y = employeeNumberOffset,
                            ),
                            title = stringResource(
                                id = R.string.employee_number,
                            ),
                            value = employeeNumber,
                            onValueChange = {
                                onEmployeeNumberChanged(it)
                            },
                            error = fieldErrEmployeeNumber,
                            keyboardType = KeyboardType.Number,
                        )
                    }

                    SimTongTextField(
                        modifier = Modifier.offset(
                            y = nameOffset,
                        ),
                        title = stringResource(
                            id = R.string.name,
                        ),
                        value = name,
                        onValueChange = {
                            onNameChanged(it)
                        },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    UnderlineBody9(
                        text = stringResource(id = R.string.sign_in_induction_msg),
                        underlineText = listOf(
                            stringResource(id = R.string.sign_in)
                        ),
                        color = SimTongColor.Gray400,
                        onClick = {
                            toSignIn()
                        },
                    )
                }
            },
            bottomContent = {
                SimTongBigRoundButton(
                    modifier = Modifier
                        .imePadding(),
                    text = stringResource(id = R.string.next),
                    round = 0.dp,
                    enabled = btnEnabled,
                ) {
                    nextBtnClick()
                }
            }
        )
    }
}
