package com.comit.feature_mypage.screen.fix.workplace

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimRadioButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.modifier.simSelectable
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body8
import com.comit.feature_mypage.R
import com.comit.feature_mypage.mvi.FixWorkPlaceSideEffect
import kotlinx.coroutines.InternalCoroutinesApi
import java.util.UUID

private const val DefaultSelected: Int = -1

private val FixWorkPlaceHeight: Dp = 60.dp

@Stable
private val FixWorkPlaceHeaderPadding = PaddingValues(
    start = 26.dp, end = 30.dp,
)

private const val FetchWorkPlaceFail = "근무지점 리스트를 불러오는데 실패했습니다."

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FixWorkPlaceScreen(
    navController: NavController,
    vm: FixWorkPlaceViewModel = hiltViewModel(),
) {
    val toast = rememberToast()

    val fixWorkPlaceContainer = vm.container
    val fixWorkPlaceState = fixWorkPlaceContainer.stateFlow.collectAsState().value
    val fixWorkPlaceSideEffect = fixWorkPlaceContainer.sideEffectFlow

    LaunchedEffect(key1 = vm) {
        vm.fetchWorkPlace()
    }

    fixWorkPlaceSideEffect.observeWithLifecycle() {
        when (it) {
            FixWorkPlaceSideEffect.ChangeWorkPlaceSuccess -> navController.popBackStack()
            FixWorkPlaceSideEffect.ChangeWorkPlaceFail -> toast(message = fixWorkPlaceState.errMsgSpotId)
            FixWorkPlaceSideEffect.FetchWorkPlaceFail -> toast(message = fixWorkPlaceState.errMsgSpotList)
        }
    }

    var selectedValue by remember { mutableStateOf(DefaultSelected) }
    val isSelect: (Int) -> Boolean = { selectedValue == it }

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            modifier = Modifier
                .padding(FixWorkPlaceHeaderPadding),
            headerText = stringResource(id = R.string.work_place_fix),
            sideBtnText = stringResource(id = R.string.check),
            enabledBackBtn = true,
            enabledTextBtn = fixWorkPlaceState.spotId != null,
            onTextBtnClicked = {
                vm.changeWorkPlace(fixWorkPlaceState.spotId!!)
            },
            onPrevious = {
                navController.popBackStack()
            },
        )

        Spacer(modifier = Modifier.height(15.dp))

        LazyColumn() {
            itemsIndexed(fixWorkPlaceState.spotList) { index, item ->
                Box(
                    modifier = Modifier
                        .height(FixWorkPlaceHeight)
                        .simSelectable(
                            selected = isSelect(index),
                            onClick = {
                                selectedValue = index
                                vm.inPutSpotId(UUID.fromString(item.id))
                            },
                            role = Role.RadioButton,
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 30.dp,
                            ),
                    ) {
                        Spacer(modifier = Modifier.height(15.dp))

                        Body4(
                            text = item.name,
                            color = SimTongColor.Gray800,
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Body8(
                            text = item.location,
                            color = SimTongColor.Gray800,
                        )
                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .wrapContentHeight(Alignment.Bottom),
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawLine(
                                start = Offset(
                                    x = 0f,
                                    y = canvasHeight,
                                ),
                                end = Offset(
                                    x = canvasWidth,
                                    y = canvasHeight,
                                ),
                                color = SimTongColor.Gray800,
                                strokeWidth = 0.1F,
                            )
                        }
                    }
                    SimRadioButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                            .padding(
                                end = 33.dp,
                            ),
                        checked = isSelect(index),
                        onCheckedChange = {
                            selectedValue = index
                            vm.inPutSpotId(UUID.fromString(item.id))
                        },
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewFixWorkPlaceScreen() {

    FixWorkPlaceScreen(
        navController = rememberNavController(),
    )
}
