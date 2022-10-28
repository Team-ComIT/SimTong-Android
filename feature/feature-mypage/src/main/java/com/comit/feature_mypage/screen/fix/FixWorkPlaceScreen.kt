package com.comit.feature_mypage.screen.fix

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.comit.core_design_system.button.SimRadioButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.modifier.simSelectable
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body8
import com.comit.feature_mypage.R

private var placeName by mutableStateOf(SignInDefault.DefaultPlaceName)

object SignInDefault {
    const val DefaultPlaceName = "근무 지점 선택"
}

data class WorkPlaceSample(
    val name: String,
    val position: String
)

private const val DefaultSelected: Int = -1

private const val ItemsSampleMapperStart: Int = 1
private const val ItemsSampleMapperEnd: Int = 100

private val FixWorkPlaceHeight: Dp = 60.dp

@Stable
private val FixWorkPlaceHeaderPadding = PaddingValues(
    start = 26.dp, end = 30.dp,
)

@Composable
internal fun FixWorkPlaceScreen(
    navController: NavController,
    items: List<WorkPlaceSample>,
) {
    val scrollState = rememberScrollState()
    var selectedValue by remember { mutableStateOf(DefaultSelected) }
    val isSelect: (Int) -> Boolean = { selectedValue == it }

    var enabledSideBtn by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            modifier = Modifier
                .padding(FixWorkPlaceHeaderPadding),
            headerText = stringResource(id = R.string.work_place_fix),
            sideBtnText = stringResource(id = R.string.check),
            enabledBackBtn = true,
            enabledTextBtn = enabledSideBtn,
            onTextBtnClicked = {
                //TODO ("수정 요청 보내기")
            },
            onPrevious = {
                navController.popBackStack()
            },
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState),
        ) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .height(FixWorkPlaceHeight)
                        .simSelectable(
                            selected = isSelect(index),
                            onClick = {
                                selectedValue = index
                                placeName = item.name
                                enabledSideBtn = true
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
                            color = SimTongColor.Gray900,
                        )

                        Spacer(modifier = Modifier.height(3.dp))

                        Body8(
                            text = item.position,
                            color = SimTongColor.Gray900,
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
                                color = SimTongColor.Gray900,
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
                            placeName = item.name
                            enabledSideBtn = true
                        },
                    )
                }
            }
        }
    }
}

internal val fakeItems =
    (ItemsSampleMapperStart..ItemsSampleMapperEnd).map {
        WorkPlaceSample("성심당 ${it}호 점", "대전광역시 서구 계룡로 598 롯데백화점 1층")
    }.toList()

@Preview(showBackground = true)
@Composable
fun PreviewFixWorkPlaceScreen() {

    FixWorkPlaceScreen(
        navController = rememberNavController(),
        items = fakeItems,
    )
}
