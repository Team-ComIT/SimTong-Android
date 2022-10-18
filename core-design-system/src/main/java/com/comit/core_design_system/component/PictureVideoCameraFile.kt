package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body6

data class IconListData(
    val icon: Painter,
    val text: String,
)

@Composable
fun PictureVideoCameraFileList(
    modifier: Modifier = Modifier,
    list: List<IconListData>,
    onClick: (Int) -> Unit = {},
    height: Dp = 32.dp,
    background: Color = SimTongColor.White,
    imageHeight: Dp = 18.dp,
    lineHeight: Float = 2F,
    lineColor: Color = SimTongColor.OtherColor.GrayDF,
    textColor: Color = SimTongColor.Black,
    imagePaddingStart: Dp = 14.dp,
    textPaddingStart: Dp = 12.dp,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(list) { index, data ->
            PictureVideoCameraFileItem(
                height = height,
                background = background,
                imageHeight = imageHeight,
                lineHeight = lineHeight,
                lineColor = lineColor,
                textColor = textColor,
                index = index,
                icon = data.icon.drawableId,
                text = data.text,
                onClick = onClick,
                imagePaddingStart = imagePaddingStart,
                textPaddingStart = textPaddingStart,
            )
        }
    }
}
@Composable
fun PictureVideoCameraFileItem(
    height: Dp = 32.dp,
    background: Color = SimTongColor.White,
    imageHeight: Dp = 18.dp,
    lineHeight: Float = 2F,
    lineColor: Color = SimTongColor.OtherColor.GrayDF,
    textColor: Color = SimTongColor.Black,
    index: Int,
    icon: Painter,
    text: String,
    onClick: (Int) -> Unit,
    imagePaddingStart: Dp = 14.dp,
    textPaddingStart: Dp = 12.dp,
) {
    if (index != 0) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = lineColor,
                strokeWidth = lineHeight,
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
            .background(background)
            .clickable { onClick(index) }
    ) {
        Image(
            painter = icon, contentDescription = "",
            modifier = Modifier
                .padding(imagePaddingStart, 0.dp, 0.dp, 0.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .height(imageHeight)
        )

        Body6(
            text = text,
            color = textColor,
            modifier = Modifier
                .padding(textPaddingStart, 0.dp, 0.dp, 0.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        )
    }
}

@Preview
@Composable
fun PictureVideoCameraFile() {
    PictureVideoCameraFileList(
        list = listOf(
            IconListData(painterResource(id = SimTongIcons.Image), "사진/동영상"),
            IconListData(painterResource(id = SimTongIcons.Photo), "카메라"),
            IconListData(painterResource(id = SimTongIcons.Link), "링크"),
        )
    )
}
