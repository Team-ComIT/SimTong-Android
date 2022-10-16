package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body5

@Composable
fun PlaceComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SimTongColor.White,
    roundedCornerShape: Dp = 20.dp,
    textColor: Color = SimTongColor.Black,
    onClick: (Int) -> Unit = {},
    list: List<String>,
    painter: Int = SimTongIcons.Others.Check,
    haveCheckImage: Boolean = true,
    lineHeight: Float = 2.5F,
    lineColor: Color = SimTongColor.Gray300
) {

    val isNeedExpansion = rememberSaveable { mutableStateOf(0) }

    LazyColumn(
        modifier = modifier
            .padding(0.24.dp, 0.dp, 0.24.dp, 0.dp)
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(roundedCornerShape)
            )
    ) {
        itemsIndexed(list) { index, text ->
            ChoosePlaceItem(
                backgroundColor = backgroundColor,
                isNeedExpansion = isNeedExpansion,
                onClick = onClick,
                text = text,
                textColor = textColor,
                index = index,
                painter = painter,
                haveCheckImage = haveCheckImage,
                lineHeight = lineHeight,
                lineColor = lineColor,
            )
        }
    }
}

@Composable
fun ChoosePlaceItem(
    backgroundColor: Color,
    isNeedExpansion: MutableState<Int>,
    onClick: (Int) -> Unit,
    text: String,
    textColor: Color,
    index: Int,
    painter: Int,
    haveCheckImage: Boolean,
    lineHeight: Float,
    lineColor: Color,
) {

    val paddingSide = 24.7.dp
    val startPaddingHeight = 20.dp
    val bodyHeight = 50.dp

    if (index != 0) {
        Canvas(
            modifier = Modifier
                .padding(paddingSide, 0.dp, paddingSide, 0.dp)
                .fillMaxWidth()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = lineColor,
                strokeWidth = lineHeight
            )
        }
    } else {
        Spacer(
            modifier = Modifier
                .padding(0.dp, startPaddingHeight, 0.dp, 0.dp)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .height(bodyHeight)
            .background(
                color = backgroundColor
            )
            .simClickable {
                onClick(index)
                isNeedExpansion.value = index
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(bodyHeight)
        ) {
            Body5(
                text = text,
                color = textColor,
                modifier = Modifier
                    .padding(start = paddingSide)
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
            )

            if (haveCheckImage) {
                CheckImage(
                    isNeedExpansion = isNeedExpansion.value,
                    painter = painter,
                    index = index,
                    paddingSide = paddingSide
                )
            }
        }
    }
}

@Composable
fun CheckImage(
    isNeedExpansion: Int,
    painter: Int,
    index: Int,
    paddingSide: Dp
) {
    if (isNeedExpansion == index) {
        Image(
            painter = painterResource(id = painter),
            contentDescription = "check image",
            modifier = Modifier
                .padding(end = paddingSide)
                .fillMaxHeight()
                .wrapContentHeight(CenterVertically)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }
}

@Preview
@Composable
fun PreviewPlaceComponent() {
    PlaceComponent(
        list = listOf("\uD83D\uDC5C      전체 지점", "\uD83D\uDCBB      근무 지점")
    )
}
