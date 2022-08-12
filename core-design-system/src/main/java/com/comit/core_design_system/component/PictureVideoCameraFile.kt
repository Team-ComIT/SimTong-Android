package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body6
import com.comit.core_design_system.theme.OtherColor

sealed class PictureVideoCameraFileData(val icon: Int, val text: String) {
    object Picture : PictureVideoCameraFileData(SimTongIcons.Image, "사진/동영상")
    object Camera : PictureVideoCameraFileData(SimTongIcons.Photo, "카메라")
    object File : PictureVideoCameraFileData(SimTongIcons.Link, "파일")
}

@Composable
fun PictureVideoCameraFileList(
    modifier: Modifier = Modifier,
    list: List<PictureVideoCameraFileData>,
    onClick: (Int) -> Unit
) {
    LazyColumn() {
        itemsIndexed(list) { index, data ->
            PictureVideoCameraFileItem(
                modifier = modifier,
                index = index,
                pictureVideoCameraFileData = data,
                onClick = onClick
            )
        }
    }
}
@Composable
fun PictureVideoCameraFileItem(
    modifier: Modifier = Modifier,
    index: Int,
    pictureVideoCameraFileData: PictureVideoCameraFileData,
    onClick: (Int) -> Unit
) {
    if (index != 0) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = OtherColor.GrayDF
            )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
            .clickable { onClick(index) }
    ) {
        Image(
            painter = painterResource(id = pictureVideoCameraFileData.icon), contentDescription = "",
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .height(18.dp)
                .padding(14.dp, 0.dp, 0.dp, 0.dp)
        )

        Body6(
            text = pictureVideoCameraFileData.text,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(12.dp, 0.dp, 0.dp, 0.dp)
        )
    }
}
