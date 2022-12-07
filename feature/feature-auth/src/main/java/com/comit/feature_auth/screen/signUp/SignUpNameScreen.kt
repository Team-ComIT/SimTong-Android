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
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.comit.feature_auth.mvi.SignUpState
import com.comit.feature_auth.utils.BottomSheetType
import com.comit.feature_auth.utils.changeBottomSheetState
import com.comit.feature_auth.vm.SignUpViewModel
import kotlinx.coroutines.launch
import kotlin.math.abs

@Stable
private val TextFieldMargin: Int = 8

@Stable
internal val TextFieldHeight: Int = 64

/**
 * TextField의 Offset을 계산합니다.
 */
private fun textFieldOffset(
    step: SignUpStep.InputUserInfo,
    currentStep: SignUpStep.InputUserInfo,
): Dp {
    return (abs(0.coerceAtMost(step.offsetIdx - currentStep.offsetIdx) * TextFieldMargin)).dp
}

@Stable
private val TextFieldEnterAnimation = fadeIn(tween(450))

internal const val SignUpBottomMargin: Int = 24

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
    state: SignUpState,
    viewModel: SignUpViewModel,
    toPrevious: () -> Unit,
    toNext: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    val nextBtnClick = {
        when (state.signUpNameStep) {
            SignUpStep.InputUserInfo.NAME -> viewModel.navigateNameStep(SignUpStep.InputUserInfo.EMPLOYEE_NUMBER)
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> viewModel.navigateNameStep(SignUpStep.InputUserInfo.EMAIL)
            SignUpStep.InputUserInfo.EMAIL -> {
                viewModel.navigateNameStep(SignUpStep.InputUserInfo.AGREED)
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
        when (state.signUpNameStep) {
            SignUpStep.InputUserInfo.AGREED -> {
                viewModel.navigateNameStep(SignUpStep.InputUserInfo.EMAIL)
                changeBottomSheetState(
                    coroutineScope = coroutineScope,
                    bottomSheetState = bottomSheetState,
                    bottomSheetType = BottomSheetType.Hide,
                )
            }
            SignUpStep.InputUserInfo.EMAIL -> viewModel.navigateNameStep(SignUpStep.InputUserInfo.EMPLOYEE_NUMBER)
            SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> viewModel.navigateNameStep(SignUpStep.InputUserInfo.NAME)
            SignUpStep.InputUserInfo.NAME -> toPrevious()
        }
    }

    val nameOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.NAME,
            currentStep = state.signUpNameStep,
        )
    )
    val employeeNumberOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMPLOYEE_NUMBER,
            currentStep = state.signUpNameStep,
        )
    )
    val emailOffset by animateDpAsState(
        textFieldOffset(
            step = SignUpStep.InputUserInfo.EMAIL,
            currentStep = state.signUpNameStep,
        )
    )

    // TODO ("추후 MVI로 개선 필요")
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

    val btnEnabled = when (state.signUpNameStep) {
        SignUpStep.InputUserInfo.NAME -> state.name.isNotEmpty()
        SignUpStep.InputUserInfo.EMPLOYEE_NUMBER -> state.employeeNumber.isNotEmpty()
        SignUpStep.InputUserInfo.EMAIL -> state.employeeNumber.isNotEmpty()
        SignUpStep.InputUserInfo.AGREED -> agreedChecked.all { it }
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
                        visible = state.signUpNameStep.offsetIdx >= SignUpStep.InputUserInfo.EMAIL.offsetIdx,
                        enter = TextFieldEnterAnimation
                    ) {
                        SimTongTextField(
                            modifier = Modifier.offset(y = emailOffset),
                            title = stringResource(
                                id = R.string.email,
                            ),
                            value = state.email,
                            onValueChange = { viewModel.changeEmail(it) },
                        )
                    }

                    AnimatedVisibility(
                        visible = state.signUpNameStep.offsetIdx >= SignUpStep.InputUserInfo.EMPLOYEE_NUMBER.offsetIdx,
                        enter = TextFieldEnterAnimation
                    ) {
                        SimTongTextField(
                            modifier = Modifier.offset(
                                y = employeeNumberOffset,
                            ),
                            title = stringResource(
                                id = R.string.employee_number,
                            ),
                            value = state.employeeNumber,
                            onValueChange = { viewModel.changeEmployeeNumber(it) },
                        )
                    }

                    SimTongTextField(
                        modifier = Modifier.offset(
                            y = nameOffset,
                        ),
                        title = stringResource(
                            id = R.string.name,
                        ),
                        value = state.name,
                        onValueChange = { viewModel.changeName(it) },
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    UnderlineBody9(
                        text = stringResource(id = R.string.sign_in_induction_msg),
                        underlineText = listOf(
                            stringResource(id = R.string.sign_in)
                        ),
                        color = SimTongColor.Gray400,
                    )
                }
            },
            bottomContent = {
                SimTongBigRoundButton(
                    modifier = Modifier.imePadding(),
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
