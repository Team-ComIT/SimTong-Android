package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body5

@Stable
private val PlaceComponentHeight: Dp = 200.dp

@Composable
fun PlaceComponent(
    modifier: Modifier = Modifier,
    backgroundColor: Color = SimTongColor.White,
    roundedCornerShape: Dp = 20.dp,
    textColor: Color = SimTongColor.Black,
    onClick: (Int) -> Unit = {},
    list: List<String>,
    icon: SimTongIcon = SimTongIcon.Check,
    haveCheckImage: Boolean = true,
    lineHeight: Float = 2.5F,
    lineColor: Color = SimTongColor.Gray300
) {

    val isNeedExpansion = rememberSaveable { mutableStateOf(0) }

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 0.24.dp)
            .fillMaxWidth()
            .height(PlaceComponentHeight)
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
                painter = painterResource(
                    id = icon.drawableId,
                ),
                haveCheckImage = haveCheckImage,
                lineHeight = lineHeight,
                lineColor = lineColor,
            )
        }
    }
}

@Stable
private val ChoosePlaceItemPaddingSide: Dp = 24.7.dp

@Stable
private val ChoosePlaceItemPaddingHeight: Dp = 20.dp

@Stable
private val ChoosePlaceItemPaddingBodyHeight: Dp = 50.dp

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

    if (index != 0) {
        Canvas(
            modifier = Modifier
                .padding(horizontal = ChoosePlaceItemPaddingSide)
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
                .padding(top = ChoosePlaceItemPaddingHeight)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.Start)
            .height(ChoosePlaceItemPaddingBodyHeight)
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
                .height(ChoosePlaceItemPaddingBodyHeight)
        ) {
            Body5(
                text = text,
                color = textColor,
                modifier = Modifier
                    .padding(start = ChoosePlaceItemPaddingSide)
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
            )

            if (haveCheckImage) {
                CheckImage(
                    isNeedExpansion = isNeedExpansion.value,
                    painter = painter,
                    index = index,
                    paddingSide = ChoosePlaceItemPaddingSide
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
            contentDescription = stringResource(id = R.string.description_ic_item),
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
