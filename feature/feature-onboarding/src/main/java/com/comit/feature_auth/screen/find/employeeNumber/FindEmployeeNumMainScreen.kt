package com.comit.feature_auth.screen.find.employeeNumber

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.BasicButton
import com.comit.core_design_system.button.SimRadioButton
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.feature_auth.R
import com.comit.feature_auth.mvi.FindEmployeeNumSideEffect
import com.comit.feature_auth.vm.FindEmployeeNumViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

const val DefaultPlace = "근무 지점 선택"

internal fun String.isPlaceEmpty(): Boolean =
    this == DefaultPlace

private const val UserNotMatchedMessage = "입력한 정보에 해당하는 유저를 찾을 수 없습니다."

private const val FindEmployeeNumberScreen: Int = 0
private const val FindEmployeeNumberResultScreen: Int = 1

@OptIn(
    ExperimentalMaterialApi::class,
    InternalCoroutinesApi::class,
)
@Composable
internal fun FindEmployeeNumMainScreen(
    findEmployeeNumViewModel: FindEmployeeNumViewModel = hiltViewModel(),
    navController: NavController,
) {
    val container = findEmployeeNumViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow

    val coroutineScope = rememberCoroutineScope()
    val localFocusManager = LocalFocusManager.current
    val bottomSheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden
    )

    val toast = rememberToast()

    var currentPage by remember { mutableStateOf(FindEmployeeNumberScreen) }

    sideEffect.observeWithLifecycle {
        when (it) {
            is FindEmployeeNumSideEffect.NavigateToResultScreen -> {
                findEmployeeNumViewModel.inputEmployeeNumber(
                    number = it.employeeNum,
                )
                currentPage = FindEmployeeNumberResultScreen
            }
            is FindEmployeeNumSideEffect.FetchSpot -> {
                coroutineScope.launch {
                    localFocusManager.clearFocus()
                    bottomSheetState.show()
                }
            }
            is FindEmployeeNumSideEffect.UserInfoNotMatched -> {
                toast(
                    message = UserNotMatchedMessage,
                )
            }
        }
    }

    Crossfade(targetState = currentPage) { pageState ->
        when (pageState) {
            FindEmployeeNumberScreen -> FindEmployeeNumberScreen(
                name = state.name,
                onNameChanged = {
                    findEmployeeNumViewModel.inputName(it)
                },
                place = state.place,
                onPlaceChanged = {
                    findEmployeeNumViewModel.inputPlace(it)
                },
                onPlacedIdChanged = {
                    findEmployeeNumViewModel.inputPlaceId(it)
                },
                email = state.email,
                onEmailChanged = {
                    findEmployeeNumViewModel.inputEmail(it)
                },
                errMsgPlace = state.errMsgPlace,
                errMsgName = state.errMsgName,
                errMsgEmail = state.errMsgEmail,
                placeList = state.placeList,
                toFetchSpot = {
                    findEmployeeNumViewModel.fetchSpot()
                },
                toFindEmployeeNum = {
                    findEmployeeNumViewModel.findEmployeeNum(
                        name = state.name,
                        spotId = state.placeId,
                        email = state.email,
                    )
                },
                coroutineScope = coroutineScope,
                localFocusManager = localFocusManager,
                bottomSheetState = bottomSheetState,
            )
            FindEmployeeNumberResultScreen -> {
                FindEmployeeNumResultScreen(
                    name = state.name,
                    employeeNumber = state.employeeNum,
                    onPrevious = {
                        navController.popBackStack()
                    },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun FindEmployeeNumberScreen(
    name: String,
    onNameChanged: (String) -> Unit,
    place: String,
    onPlaceChanged: (String) -> Unit,
    onPlacedIdChanged: (String) -> Unit,
    email: String,
    onEmailChanged: (String) -> Unit,
    errMsgPlace: String?,
    errMsgName: String?,
    errMsgEmail: String?,
    placeList: List<SpotUiModel>,
    toFetchSpot: () -> Unit,
    toFindEmployeeNum: () -> Unit,
    coroutineScope: CoroutineScope,
    localFocusManager: FocusManager,
    bottomSheetState: ModalBottomSheetState,
) {

    val underButtonEnabled =
        !(name.isEmpty() || place.isPlaceEmpty() || email.isEmpty())

    val centerButtonTextColor =
        if (place == stringResource(id = R.string.choose_work_place))
            SimTongColor.Gray300 else SimTongColor.Gray800

    val centerButtonColor =
        if (place == stringResource(id = R.string.choose_work_place))
            SimTongColor.Gray100 else SimTongColor.Gray50

    val centerButtonBorderColor =
        if (errMsgPlace == null) SimTongColor.Gray100 else SimTongColor.Error

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            FindPlaceBottomSheetContent(
                placeList = placeList,
                onSelectedPlace = { name, id ->
                    onPlaceChanged(name)
                    onPlacedIdChanged(id)
                    coroutineScope.launch {
                        bottomSheetState.hide()
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 40.dp)
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            SimTongTextField(
                value = name,
                onValueChange = {
                    onNameChanged(it)
                },
                hintBackgroundColor = SimTongColor.Gray100,
                backgroundColor = SimTongColor.Gray50,
                hint = stringResource(id = R.string.name),
                error = errMsgName
            )

            Spacer(modifier = Modifier.height(20.dp))

            // TODO limsaehyun - 디자인 시스템 활용해야 함
            BasicButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(44.dp)
                    .border(
                        width = 1.dp,
                        color = centerButtonBorderColor,
                        shape = RoundedCornerShape(5.dp)
                    ),
                shape = RoundedCornerShape(5.dp),
                enabled = true,
                onClick = toFetchSpot,
                backgroundColor = centerButtonColor,
                pressedBackgroundColor = SimTongColor.Gray100,
                disabledBackgroundColor = SimTongColor.Gray100,
            ) {
                Body6(
                    text = place,
                    color = centerButtonTextColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 14.dp),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            SimTongTextField(
                value = email,
                onValueChange = {
                    onEmailChanged(it)
                },
                hintBackgroundColor = SimTongColor.Gray100,
                backgroundColor = SimTongColor.Gray50,
                hint = stringResource(id = R.string.eng_email),
                error = errMsgEmail,
                keyboardType = KeyboardType.Email,
            )

            Spacer(modifier = Modifier.height(30.dp))

            SimTongBigRoundButton(
                text = stringResource(id = R.string.find_employee),
                onClick = {
                    localFocusManager.clearFocus()
                    toFindEmployeeNum()
                },
                enabled = underButtonEnabled,
            )
        }
    }
}

@Composable
internal fun FindPlaceBottomSheetContent(
    placeList: List<SpotUiModel>,
    onSelectedPlace: (String, String) -> Unit,
) {
    var checkedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .padding(
                horizontal = 30.dp,
                vertical = 32.dp,
            )
    ) {
        Body1(
            modifier = Modifier,
            text = stringResource(id = R.string.choose_place),
        )

        Spacer(modifier = Modifier.height(17.dp))

        LazyColumn {
            itemsIndexed(placeList) { index, place ->
                FindPlaceBottomSheetContentPlaceBlock(
                    name = place.name,
                    location = place.location,
                    onClick = {
                        onSelectedPlace(it, place.id)
                    },
                    index = index,
                    isChecked = checkedIndex == index,
                    onChecked = {
                        checkedIndex = it
                    },
                )
            }
        }
    }
}

@Composable
private fun FindPlaceBottomSheetContentPlaceBlock(
    index: Int,
    name: String,
    location: String,
    onClick: (String) -> Unit,
    isChecked: Boolean,
    onChecked: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.height(59.dp),
    ) {
        Divider(
            color = SimTongColor.Gray500,
            thickness = 0.1.dp,
        )

        Row(
            modifier = Modifier
                .padding(vertical = 10.dp)
                .simClickable(
                    rippleEnabled = false,
                ) {
                    onChecked(index)
                    onClick(name)
                },
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Body4(
                    text = name,
                    color = SimTongColor.Gray800,
                )
                Body8(
                    text = location,
                    color = SimTongColor.Gray600,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            SimRadioButton(
                checked = isChecked,
                onCheckedChange = {},
            )
        }
    }
}

@Composable
private fun FindEmployeeNumResultScreen(
    name: String,
    employeeNumber: String,
    onPrevious: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(183.dp))

        Body4(
            text = "$name 회원님이\n요청하는 회원 정보입니다.",
            color = SimTongColor.Gray800,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(5.dp))

        Body3(
            text = employeeNumber,
            color = SimTongColor.Gray800,
            modifier = Modifier
                .background(
                    color = SimTongColor.Gray200,
                    shape = RoundedCornerShape(4.dp),
                )
                .padding(
                    horizontal = 54.dp,
                    vertical = 5.dp,
                )
        )

        Spacer(modifier = Modifier.weight(1f))

        SimTongBigRoundButton(
            text = "로그인 창으로 돌아가기",
            onClick = onPrevious,
            modifier = Modifier.padding(horizontal = 40.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Preview
@Composable
fun PreviewShowEmployeeScreen() {
    FindEmployeeNumResultScreen(
        name = "임세현",
        employeeNumber = "123213112",
    ) {
    }
}
