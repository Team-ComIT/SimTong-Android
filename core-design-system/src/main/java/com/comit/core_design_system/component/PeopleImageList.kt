package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.theme.Body12
import com.comit.core_design_system.theme.SimTongColor
import com.skydoves.landscapist.glide.GlideImage

@Stable
private val PeopleImageListMax: Int = 99

@Composable
fun PeopleImageList(
    modifier: Modifier = Modifier,
    itemWidth: Dp,
    list: List<String?>? = null,
    nullPainter: Painter,
    showImage: Int,
    showListSize: Boolean = false,
    showListSizePainter: Painter = painterResource(
        id = R.drawable.img_notice_board_rectangle
    ),
    paddingEnd: Double = 15.0
) {
    Box(modifier = modifier) {
        if (list != null) {
            if (showListSize && list.size > showImage) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .width(itemWidth)
                ) {
                    LoadImage(
                        list = listOf(null),
                        itemWidth = itemWidth,
                        index = -1,
                        nullPainter = showListSizePainter,
                        paddingEnd = paddingEnd
                    )
                    if (list.size - showImage <= PeopleImageListMax) {
                        Body12(
                            text = (list.size - showImage).toString() + "+",
                            color = SimTongColor.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        )
                    } else {
                        Body12(
                            text = "99+",
                            color = SimTongColor.White,
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(Alignment.Center)
                        )
                    }
                }
            }

            repeat(showImage) {
                LoadImage(
                    list = list,
                    itemWidth = itemWidth,
                    index = it,
                    nullPainter = nullPainter,
                    paddingEnd = paddingEnd
                )
            }
        }
    }
}

@Composable
private fun LoadImage(
    list: List<String?>,
    itemWidth: Dp,
    index: Int,
    nullPainter: Painter,
    paddingEnd: Double
) {
    Box(
        modifier = Modifier
            .padding(0.dp, 0.dp, ((index + 1) * paddingEnd).dp, 0.dp)
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .width(itemWidth)
            .height(itemWidth)
            .clip(CircleShape)
            .background(
                SimTongColor.White
            )
    ) {
        if (index == -1) {
            Image(
                painter = nullPainter,
                contentDescription = "people image",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(itemWidth - 2.dp)
                    .clip(CircleShape)
            )
        } else {
            GlideImage(
                imageModel = list[index],
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center)
                    .size(itemWidth - 2.dp)
                    .clip(CircleShape),
                failure = {
                    Image(
                        painter = nullPainter,
                        contentDescription = "null painter image",
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(Alignment.Center)
                            .size(itemWidth - 2.dp)
                            .clip(CircleShape)
                    )
                }
            )
        }
    }
}

@Stable
private val PreviewShowImage: Int = 2

@Preview
@Composable
fun Show() {
    PeopleImageList(
        nullPainter = painterResource(id = R.drawable.img_notice_board_rectangle),
        showImage = PreviewShowImage,
        modifier = Modifier
            .height(21.dp),
        itemWidth = 21.dp,
        list = listOf("1", "2", "3", "4", "5", "6"),
        paddingEnd = 15.0,
        showListSize = true
    )
}
