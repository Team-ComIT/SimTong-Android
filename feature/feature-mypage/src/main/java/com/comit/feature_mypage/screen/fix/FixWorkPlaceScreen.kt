package com.comit.feature_mypage.screen.fix

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.button.SimCheckBox
import com.comit.core_design_system.button.SimRadioButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.Header
import com.comit.core_design_system.modifier.simSelectable
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body8
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

@Composable
fun FixWorkPlaceScreen(

){
    val scrollState = rememberScrollState()
    var selectedValue by remember { mutableStateOf(DefaultSelected) }
    val isSelect: (Int) -> Boolean = { selectedValue == it }

    var textBtnClick by remember { mutableStateOf(false) }
    val textBtnColor = if(textBtnClick) SimTongColor.MainColor else SimTongColor.MainColor200

    val items =
        (ItemsSampleMapperStart..ItemsSampleMapperEnd).map {
            WorkPlaceSample("성심당 ${it}호 점", "대전광역시 서구 계룡로 598 롯데백화점 1층")
        }.toList()

    Column(modifier = Modifier.fillMaxSize()) {
        Header(
            headerText = "근무지점 수정",
            sideBtnText = "확인",
            enabledBackBtn = true,
            onTextBtnClicked = {},
            modifier = Modifier
                .padding(start = 26.dp, end = 30.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
        ) {
            items.forEachIndexed { index,item ->
                Box(
                    modifier = Modifier
                        .height(60.dp)
                        .simSelectable(
                            selected = isSelect(index),
                            onClick = {
                                selectedValue = index
                                placeName = item.name
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

@Preview(showBackground = true)
@Composable
fun PreviewFixWorkPlaceScreen(){
    FixWorkPlaceScreen()
}