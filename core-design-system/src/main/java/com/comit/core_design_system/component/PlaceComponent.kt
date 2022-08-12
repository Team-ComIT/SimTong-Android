package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.theme.Body5
import com.comit.core_design_system.theme.SimTongColor

/*modifier = Modifier
.padding(0.7.dp, 15.dp, 0.7.dp, 0.dp)
.fillMaxWidth()
.height(200.dp)
.background(
color = Color.White,
shape = RoundedCornerShape(20.dp)*/

@Composable
fun ChoosePlace(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    list: List<String>,
    painter: Painter = painterResource(id = R.drawable.ic_check)
) {

    val isNeedExpansion = rememberSaveable { mutableStateOf(0) }

    LazyColumn(modifier = modifier) {
        itemsIndexed(list) { index, text ->
            ChoosePlaceItem(
                isNeedExpansion = isNeedExpansion,
                onClick = onClick,
                text = text,
                index = index,
                painter = painter
            )
        }
    }
}

@Composable
fun ChoosePlaceItem(
    isNeedExpansion: MutableState<Int>,
    onClick: (Int) -> Unit,
    text: String,
    index: Int,
    painter: Painter
) {
    if (index != 0) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
                .padding(24.dp, 10.dp, 24.dp, 0.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = SimTongColor.Gray300
            )
        }
    }

    Row(
        modifier = Modifier
            .padding(0.dp, 20.dp, 0.dp, 0.dp)
            .fillMaxWidth()
            .height(24.dp)
            .clickable {
                onClick(index)
                isNeedExpansion.value = index
            },
    ) {
        Body5(
            text = text,
            modifier = Modifier
                .padding(24.dp, 0.dp, 0.dp, 0.dp)
        )

        CheckImage(
            isNeedExpansion = isNeedExpansion.value,
            painter = painter,
            index = index
        )
    }
}

@Composable
fun CheckImage(isNeedExpansion: Int, painter: Painter, index: Int) {
    if (isNeedExpansion == index) {
        Image(
            painter = painter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .padding(0.dp, 0.dp, 24.dp, 0.dp)
        )
    }
}
