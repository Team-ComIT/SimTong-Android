package com.comit.core_design_system.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body5
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.calculateCurrentOffsetForPage
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.yield
import kotlin.math.absoluteValue

@ExperimentalPagerApi
@Composable
fun NoticeBoardLazyColumn(
    modifier: Modifier = Modifier,
    list: List<NoticeBoardData>,
    menuClick: () -> Unit = {},
    heartClick: () -> Unit = {},
    commentClick: () -> Unit = {}
) {
    LazyColumn() {
        items(list) {
            NoticeBoardItem(
                modifier = modifier,
                data = it,
                menuClick = menuClick,
                heartClick = heartClick,
                commentClick = commentClick
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun NoticeBoardItem(
    modifier: Modifier,
    data: NoticeBoardData,
    menuClick: () -> Unit,
    heartClick: () -> Unit,
    commentClick: () -> Unit
) {
    val like = rememberSaveable { mutableStateOf(data.like) }

    Card() {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                Box(
                    modifier = Modifier
                        .padding(20.dp, 0.dp, 0.dp, 0.dp)
                        .width(30.dp)
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .height(30.dp)
                        .background(
                            shape = CircleShape,
                            color = Color.LightGray
                        )
                ) {
                    Image(
                        painter = painterResource(
                            id = R.drawable.img_notice_board_rectangle
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .width(30.dp)
                            .height(30.dp)
                            .clip(CircleShape)
                        // contentScale = ContentScale.Crop
                    )
                }

                Body5(
                    text = data.title + " • " + data.time,
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )

                Image(
                    painter = painterResource(
                        id = SimTongIcon.Option_Thin.drawableId,
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End)
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(0.dp, 0.dp, 20.dp, 0.dp)
                        .clickable { menuClick() }
                )
            }
            ViewPagerSliderNoticeBoard()

            Row(
                modifier = Modifier
                    .padding(20.dp, 10.dp, 0.dp, 10.dp)
                    .fillMaxWidth()
            ) {

                Image(
                    painter = painterResource(
                        id = if (like.value) SimTongIcon.Heart_On.drawableId
                        else SimTongIcon.Heart_Off.drawableId,
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .noRippleClickable {
                            like.value = !like.value
                            heartClick()
                        }
                )

                Image(
                    painter = painterResource(
                        id = SimTongIcon.Comment.drawableId,
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 0.dp, 0.dp)
                        .width(26.dp)
                        .noRippleClickable { commentClick() }
                )
            }

            Row(
                modifier = Modifier
            ) {
                PeopleImageList(
                    itemWidth = 20.dp,
                    nullPainter = painterResource(id = R.drawable.img_notice_board_rectangle),
                    showImage = R.drawable.img_notice_board_rectangle,
                    list = listOf("1", "2", "3")
                )
            }
        }
    }
}

@Stable
private val ViewPageSliderNoticeBoardAnimationSpec: Int = 600

@Stable
private val ViewPageSliderNoticeBoardDelay: Long = 2000

@ExperimentalPagerApi
@Composable
fun ViewPagerSliderNoticeBoardHorizontalPager(
    pagerState: PagerState
) {
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        Card(
            modifier = Modifier
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
        ) {
            val item = viewPagerNoticeBoardImageList[it]
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(
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
}

@ExperimentalPagerApi
@Composable
fun ViewPagerSliderNoticeBoard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
    ) {
        val pagerState = rememberPagerState(
            pageCount = viewPagerNoticeBoardImageList.size,
            initialPage = 0
        )

        LaunchedEffect(Unit) {
            while (true) {
                yield()
                delay(ViewPageSliderNoticeBoardDelay)
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage) % (pagerState.pageCount),
                    animationSpec = tween(ViewPageSliderNoticeBoardAnimationSpec)
                )
            }
        }

        ViewPagerSliderNoticeBoardHorizontalPager(pagerState = pagerState)

        Box(
            modifier = Modifier
                .padding(0.dp, 0.dp, 0.dp, 10.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .height(24.dp)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .width(48.dp)
                .background(
                    color = SimTongColor.Black.copy(alpha = 0.1f),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Body11(
                text = (pagerState.currentPage + 1).toString() + "/" + pagerState.pageCount,
                color = SimTongColor.White,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize()
            )
        }
        /*HorizontalPagerIndicator(
            activeColor = SimTongColor.MainColor,
            inactiveColor = SimTongColor.OtherColor.GrayD9,
            pagerState = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .padding(16.dp)
        )*/
    }
}

data class NoticeBoardData(
    val title: String,
    val time: String,
    val like: Boolean
)

val viewPagerNoticeBoardImageList = listOf(
    R.drawable.img_notice_board_rectangle,
    R.drawable.img_notice_board_rectangle,
    R.drawable.img_notice_board_rectangle,
    R.drawable.img_notice_board_rectangle
)

@ExperimentalPagerApi
@Preview
@Composable
fun NoticeBoard() {
    NoticeBoardLazyColumn(
        list = listOf(
            NoticeBoardData(
                "제목",
                "1시간 전",
                false
            )
        )
    )
}
