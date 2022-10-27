package com.comit.feature_auth.screen.find.findemployeenum

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.BasicButton
import com.comit.core_design_system.button.SimRadioButton
import com.comit.core_design_system.button.SimTongBigRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.SimTongTextField
import com.comit.core_design_system.modifier.simSelectable
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body2
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.Body8
import com.comit.feature_auth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private var placeName by mutableStateOf(SignInDefault.DefaultPlaceName)

object SignInDefault {
    const val DefaultPlaceName = "근무 지점 선택"
}

fun String.isPlaceEmpty(): Boolean =
    this == SignInDefault.DefaultPlaceName

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FindEmployeeNumScreen(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState,
) {
    var name by remember { mutableStateOf<String?>(null) }
    var eMail by remember { mutableStateOf<String?>(null) }
    var nameError by remember { mutableStateOf<String?>(null) }
    var workPlaceError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    val underButtonEnabled = !(name.isNullOrEmpty() || placeName.isPlaceEmpty() || eMail.isNullOrEmpty())

    val errorMsg = stringResource(id = R.string.error_message)

    val centerButtonTextColor =
        if (placeName == stringResource(id = R.string.choose_work_place))
            SimTongColor.Gray400 else SimTongColor.Gray900
    val centerButtonColor =
        if (placeName == stringResource(id = R.string.choose_work_place))
            SimTongColor.Gray200 else SimTongColor.Gray100
    val centerButtonBorderColor =
        if (workPlaceError == null) SimTongColor.Gray200 else SimTongColor.Error

    val localFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(horizontal = 40.dp)
    ) {

        Spacer(modifier = Modifier.height(25.dp))

        SimTongTextField(
            value = name ?: "",
            onValueChange = {
                name = it
                nameError = null
                workPlaceError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.name),
            error = nameError
        )

        Spacer(modifier = Modifier.height(20.dp))

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
            onClick = {
                coroutineScope.launch {
                    nameError = null
                    workPlaceError = null
                    emailError = null
                    localFocusManager.clearFocus()
                    bottomSheetState.show()
                }
            },
            backgroundColor = centerButtonColor,
            pressedBackgroundColor = SimTongColor.Gray200,
            disabledBackgroundColor = SimTongColor.Gray200,
        ) {
            Body6(
                text = placeName,
                color = centerButtonTextColor,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
                    .padding(start = 14.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        SimTongTextField(
            value = eMail ?: "",
            onValueChange = {
                eMail = it
                nameError = null
                workPlaceError = null
                emailError = null
            },
            hintBackgroundColor = SimTongColor.Gray200,
            backgroundColor = SimTongColor.Gray100,
            hint = stringResource(id = R.string.eng_email),
            error = emailError
        )

        Spacer(modifier = Modifier.height(30.dp))

        SimTongBigRoundButton(
            text = stringResource(id = R.string.find_employee),
            onClick = {
                nameError = ""
                workPlaceError = ""
                emailError = errorMsg
                localFocusManager.clearFocus()
            },
            enabled = underButtonEnabled
        )
    }
}

data class FindPlaceDataSample(
    val name: String,
    val position: String
)

private const val DefaultSelected: Int = -1

private const val BottomSheetStateHideDelay: Long = 400
private const val ItemsSampleMapperStart: Int = 1
private const val ItemsSampleMapperEnd: Int = 100

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FindPlaceLazyColumn(
    coroutineScope: CoroutineScope,
    bottomSheetState: ModalBottomSheetState
) {
    val scrollState = rememberScrollState()
    var selectedValue by remember { mutableStateOf(DefaultSelected) }

    val isSelect: (Int) -> Boolean = { selectedValue == it }

    val items =
        (ItemsSampleMapperStart..ItemsSampleMapperEnd).map {
            FindPlaceDataSample("성심당 ${it}호 점", "대전광역시 서구 계룡로 598 롯데백화점 1층")
        }.toList()

    Column {
        Body1(
            text = stringResource(id = R.string.choose_place),
            modifier = Modifier
                .padding(start = 30.dp, top = 32.dp)
        )

        Spacer(modifier = Modifier.height(17.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .simSelectable(
                            selected = isSelect(index),
                            onClick = {
                                selectedValue = index
                                placeName = item.name
                                coroutineScope.launch {
                                    delay(BottomSheetStateHideDelay)
                                    bottomSheetState.hide()
                                }
                            },
                            role = Role.RadioButton
                        )
                ) {

                    Column(
                        modifier = Modifier
                            .padding(horizontal = 30.dp)
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))

                        Body4(
                            text = item.name,
                            color = SimTongColor.Gray900
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Body8(
                            text = item.position,
                            color = SimTongColor.Gray900
                        )
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .wrapContentHeight(Alignment.Bottom)
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawLine(
                                start = Offset(x = 0f, y = canvasHeight),
                                end = Offset(x = canvasWidth, y = canvasHeight),
                                color = SimTongColor.Gray900,
                                strokeWidth = 0.1F
                            )
                        }
                    }

                    SimRadioButton(
                        checked = isSelect(index),
                        onCheckedChange = {
                            selectedValue = index
                            placeName = item.name
                            coroutineScope.launch {
                                delay(BottomSheetStateHideDelay)
                                bottomSheetState.hide()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(end = 33.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowEmployeeNum() {
    Column() {
        Spacer(modifier = Modifier.height(227.5.dp))

        Body2(
            text = "회원님의 사원번호는",
            color = SimTongColor.Gray900,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        Body1(
            text = "12345678",
            color = SimTongColor.Gray900,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        Body2(
            text = "입니다.",
            color = SimTongColor.Gray900,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally),
        )

        SimTongBigRoundButton(
            text = "로그인 창으로 돌아가기",
            onClick = {},
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .padding(horizontal = 40.dp)
                .padding(bottom = 50.dp)
        )
    }
}

/*@ExperimentalMaterialApi
@Composable
fun bottomSheet(

) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )

    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(572.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "BottomSheet",
                    fontSize = 60.sp
                )
            }
        },
        sheetBackgroundColor = SimTongColor.White,
        sheetPeekHeight = 20.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.isCollapsed) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                }
            }) {
                Text(text = "Toggle Sheet")
            }
        }
    }
}*/

@Preview
@Composable
fun PreviewShowEmployeeScreen() {
    ShowEmployeeNum()
}
