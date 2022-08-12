package com.comit.core_design_system.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.ui.util.lerp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body5
import com.comit.core_design_system.theme.OtherColor
import com.comit.core_design_system.theme.SimTongColor
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun NoticeBoardLazyColumn(list: List<NoticeBoardData>){
    LazyColumn(){
        items(list){
            NoticeBoardItem()
        }
    }
}

@ExperimentalPagerApi
@Composable
fun NoticeBoardItem(){
    Card() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
            ) {
                Box(modifier = Modifier
                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                    .width(30.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .height(30.dp)
                    .background(
                        shape = CircleShape,
                        color = Color.LightGray
                    )
                ){
                    Image(painter = painterResource(
                        id = R.drawable.img_item_carrot),
                        contentDescription = "",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clip(CircleShape)
                        //contentScale = ContentScale.Crop
                    )
                }

                Body5(text = "로쏘(주) 전체 지점 ",
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )
            }
            ViewPagerSliderNoticeBoard()

            Row(modifier = Modifier
                .padding(20.dp, 10.dp, 0.dp, 10.dp)
                .fillMaxWidth()
            ) {
                //HeartBtn(check = false, firstSize = 26.dp, secondSize = 28.dp)
                SimTongIcons.Heart(false)

                Image(painter = painterResource(
                    id = SimTongIcons.Comment), contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .width(26.dp)
                        .clickable { }
                )

            }
        }
    }

}

@ExperimentalPagerApi
@Composable
fun ViewPagerSliderNoticeBoard(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .aspectRatio(1f)
    ){
        val pagerState = rememberPagerState(
            pageCount = viewPagerNoticeBoardImageList.size,
            initialPage = 0
        )

        LaunchedEffect(Unit){
            while (true){
                yield()
                delay(2000)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage) % (pagerState.pageCount),
                    animationSpec = tween(600)
                )

            }
        }

        HorizontalPager(state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        ) {
            Card(modifier = Modifier
                .graphicsLayer {
                    val pageOffset = calculateCurrentOffsetForPage(page = it).absoluteValue
                    lerp(
                        start = 0.85f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    ).also { scale ->
                        scaleX = scale
                        scaleY = scale
                    }
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
                .fillMaxWidth()
            ){
                val item = viewPagerNoticeBoardImageList[it]
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.Center)
                ) {
                    Image(painter = painterResource(
                        id = item
                    ),
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                    )
                }

            }
        }
        HorizontalPagerIndicator(
            activeColor = SimTongColor.MainColor,
            inactiveColor = OtherColor.GrayD9,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp)
        )
    }

}

data class NoticeBoardData(
    val title: String
)

data class NoticeBoardImageData(
    val imageUri: Int
)

val viewPagerNoticeBoardImageList = listOf(
    /*NoticeBoardImageData(
        R.drawable.img_notice_board_rectangle
    )*/
    R.drawable.img_notice_board_rectangle
)
