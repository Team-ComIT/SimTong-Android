package com.comit.feature_auth.screen.signup

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BigRedRoundButton
import com.comit.core_design_system.button.SimTextCheckBox
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body9
import com.comit.feature_auth.R
import com.comit.feature_auth.utils.BottomSheetType
import com.comit.feature_auth.utils.changeBottomSheetState
import kotlinx.coroutines.launch
import kotlin.math.abs

@Stable
private val TextFieldMargin: Int = 8

/**
 * TextField의 Offset을 계산합니다.
 */
internal fun textFieldOffset(
    step: SignUpStep.InputUserInfo,
    currentStep: SignUpStep.InputUserInfo,
): Dp {
    return (abs(0.coerceAtMost(step.offsetIdx - currentStep.offsetIdx) * TextFieldMargin)).dp
}

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

private const val SignUpBottomMargin: Int = 24

/**
 * 약관 목록을 정이합니다.
 */
private val agreedList: List<String> =
    listOf(
        "약관1",
        "약관2",
        "약관3",
        "약관4"
    )

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpNameScreen(
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    var signUpStep: SignUpStep.InputUserInfo by remember {
        mutableStateOf(SignUpStep.InputUserInfo.NAME)
    }

    val nextBtnClick = {
        when (signUpStep) {
            SignUpStep.InputUserInfo.NAME -> signUpStep = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> signUpStep = SignUpStep.InputUserInfo.EMAIL
            SignUpStep.InputUserInfo.EMAIL -> {
                signUpStep = SignUpStep.InputUserInfo.AGREED
                changeBottomSheetState(
                    coroutineScope = coroutineScope,
                    bottomSheetState = bottomSheetState,
                    bottomSheetType = BottomSheetType.Show
                )
            }
            SignUpStep.InputUserInfo.AGREED -> toNext()
        }
    }

    val backBtnClick = {
        when (signUpStep) {
            SignUpStep.InputUserInfo.AGREED -> {
                signUpStep = SignUpStep.InputUserInfo.EMAIL
                changeBottomSheetState(
                    coroutineScope = coroutineScope,
                    bottomSheetState = bottomSheetState,
                    bottomSheetType = BottomSheetType.Hide
                )
            }
            SignUpStep.InputUserInfo.EMAIL -> signUpStep = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> signUpStep = SignUpStep.InputUserInfo.NAME
            SignUpStep.InputUserInfo.NAME -> toPrevious()
        }
    }

    val nameOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.NAME,
            currentStep = signUpStep,
        )
    )
    val employeeNumberOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER,
            currentStep = signUpStep,
        )
    )
    val emailOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMAIL,
            currentStep = signUpStep,
        )
    )
    val bottomLoreOffset by animateDpAsState(
        targetValue = (signUpStep.offsetIdx * TextFieldMargin + SignUpBottomMargin).dp
    )

    var name by remember { mutableStateOf<String?>(null) }
    var employeeNumber by remember { mutableStateOf<String?>(null) }
    var email by remember { mutableStateOf<String?>(null) }

    val btnEnabled = when (signUpStep) {
        SignUpStep.InputUserInfo.NAME -> name != null
        SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> employeeNumber != null
        SignUpStep.InputUserInfo.EMAIL -> email != null
        SignUpStep.InputUserInfo.AGREED -> true // TODO checkALL
    }

    // 약관 모두 동의 개선 필요
    var agreedAll by remember {
        mutableStateOf(false)
    }

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
                        checked = agreedAll,
                        onCheckedChange = { agreedAll = !agreedAll },
                    )

                    Divider(
                        thickness = 1.dp,
                        color = SimTongColor.Gray800,
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

                BigRedRoundButton(
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
        BaseSignUpScreen(
            onPrevious = backBtnClick,
            onNext = nextBtnClick,
            btnEnabled = btnEnabled,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(horizontal = 40.dp),
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = signUpStep.offsetIdx >= SignUpStep.InputUserInfo.EMAIL.offsetIdx,
                    enter = TextFieldEnterAnimation
                ) {
                    SimTongTextField(
                        modifier = Modifier.offset(y = emailOffset),
                        title = stringResource(
                            id = R.string.email,
                        ),
                        value = email ?: "",
                        onValueChange = { email = it },
                    )
                }

                AnimatedVisibility(
                    visible = signUpStep.offsetIdx >= SignUpStep.InputUserInfo.EMPLOYEE_NUMBER.offsetIdx,
                    enter = TextFieldEnterAnimation
                ) {
                    SimTongTextField(
                        modifier = Modifier.offset(
                            y = employeeNumberOffset,
                        ),
                        title = stringResource(
                            id = R.string.employee_number,
                        ),
                        value = employeeNumber ?: "",
                        onValueChange = { employeeNumber = it },
                    )
                }

                SimTongTextField(
                    modifier = Modifier.offset(
                        y = nameOffset,
                    ),
                    title = stringResource(
                        id = R.string.name,
                    ),
                    value = name ?: "",
                    onValueChange = { name = it },
                )

                Row(
                    modifier = Modifier.offset(
                        y = bottomLoreOffset,
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Body9(
                        text = stringResource(
                            id = R.string.sign_lore_have_account,
                        ),
                        color = SimTongColor.Gray500,
                    )

                    Body9(
                        modifier = Modifier.padding(
                            start = 3.dp,
                        ),
                        text = stringResource(
                            id = R.string.sign_in,
                        ),
                        color = SimTongColor.Gray500,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUp() {
    SignUpNameScreen(
        toPrevious = { },
    ) {
    }
}
