package com.comit.core_design_system.component

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body8
import com.comit.core_design_system.typography.Body9
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
fun NoticeBoard(
    modifier: Modifier = Modifier,
    list: List<NoticeBoardData>,
    onMenuClicked: (Int) -> Unit = {},
    onHeartClicked: (Int) -> Unit = {},
    onCommentClicked: (Int) -> Unit = {}
) {
    LazyColumn {
        items(list) {
            NoticeBoardItem(
                modifier = modifier,
                data = it,
                onMenuClicked = onMenuClicked,
                onHeartClicked = onHeartClicked,
                onCommentClicked = onCommentClicked
            )
        }
    }
}

@ExperimentalPagerApi
@Composable
fun NoticeBoardItem(
    modifier: Modifier,
    data: NoticeBoardData,
    onMenuClicked: (Int) -> Unit,
    onHeartClicked: (Int) -> Unit,
    onCommentClicked: (Int) -> Unit
) {

    Card() {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            NoticeBoardItemRowTop(
                profile = data.profile,
                title = data.title,
                time = data.time,
                onMenuClicked = onMenuClicked
            )

            ViewPagerSliderNoticeBoard(
                // list = data.imageList
            )

            NoticeBoardItemHeartChat(
                dataLike = data.like,
                heartClick = onHeartClicked,
                commentClick = onCommentClicked
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start)
            ) {
                PeopleImageList(
                    itemWidth = 20.dp,
                    nullPainter = painterResource(id = R.drawable.img_notice_board_rectangle),
                    showImage = 3,
                    list = listOf("1", "2", "3")
                )
            }

            Row(
                modifier = Modifier
                    .height(20.dp)
            ) {
                Spacer(modifier = Modifier.width(20.dp))

                Body9(text = data.placeName)

                Body10(text = data.contentText)
            }

            Body8(
                text = "댓글 " + 4.toString() + "개 모두 보기",
                color = SimTongColor.Gray500,
                modifier = Modifier
                    .padding(start = 20.dp, top = 4.dp)
                    .noRippleClickable { }
            )
        }
    }
}

@Stable
private val NoticeBoardItemRowTopHeight: Dp = 50.dp

@Stable
private val NoticeBoardItemRowTopImageSize: Dp = 30.dp

@Composable
fun NoticeBoardItemRowTop(
    profile: String?,
    title: String,
    time: String,
    onMenuClicked: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(NoticeBoardItemRowTopHeight)
    ) {
        if (profile.isNullOrEmpty()) {
            Image(
                painter = painterResource(
                    id = R.drawable.img_notice_board_rectangle
                ),
                contentDescription = stringResource(id = R.string.description_ic_profile),
                modifier = Modifier
                    .padding(start = 20.dp)
                    .width(NoticeBoardItemRowTopImageSize)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .height(NoticeBoardItemRowTopImageSize)
                    .clip(CircleShape)
            )
        }

        Body5(
            text = "$title • $time",
            modifier = Modifier
                .padding(start = 15.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Image(
            painter = painterResource(
                id = SimTongIcon.Option_Thin.drawableId,
            ),
            contentDescription = stringResource(id = R.string.description_ic_option),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(end = 20.dp)
                .simClickable { onMenuClicked(1) }
        )
    }
}

@Stable
private val NoticeBoardItemHeartChatCommentWidth: Dp = 26.dp

@Composable
fun NoticeBoardItemHeartChat(
    dataLike: Boolean,
    heartClick: (Int) -> Unit,
    commentClick: (Int) -> Unit
) {
    val like = rememberSaveable { mutableStateOf(dataLike) }

    Row(
        modifier = Modifier
            .padding(start = 20.dp, top = 10.dp, end = 10.dp)
            .fillMaxWidth()
    ) {

        Image(
            painter = painterResource(
                id = if (like.value) SimTongIcon.Heart_On.drawableId else
                    SimTongIcon.Heart_Off.drawableId
            ),
            contentDescription = stringResource(id = R.string.description_ic_heart),
            modifier = Modifier
                .noRippleClickable {
                    like.value = !like.value
                    heartClick(1)
                }
        )

        Image(
            painter = painterResource(
                id = SimTongIcon.Comment.drawableId,
            ),
            contentDescription = stringResource(id = R.string.description_ic_comment),
            modifier = Modifier
                .padding(start = 15.dp)
                .width(NoticeBoardItemHeartChatCommentWidth)
                .noRippleClickable { commentClick(1) }
        )
    }
}

@Stable
private val ViewPageSliderNoticeBoardAnimationSpec: Int = 600

@Stable
private val ViewPageSliderNoticeBoardDelay: Long = 2000

@ExperimentalPagerApi
@Composable
fun ViewPagerSliderNoticeBoardHorizontalPager(
    pagerState: PagerState,
    // list: List<String>?
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
            // val item = viewPagerNoticeBoardImageList[it]

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.Center)
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.img_notice_board_rectangle
                    ),
                    contentDescription = stringResource(id = R.string.description_ic_item),
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

@Stable
private val ViewPagerSliderNoticeBoardHeight: Dp = 24.dp

@Stable
private val ViewPagerSliderNoticeBoardWidth: Dp = 48.dp

@ExperimentalPagerApi
@Composable
fun ViewPagerSliderNoticeBoard(
    // list: List<String>?
) {
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

        ViewPagerSliderNoticeBoardHorizontalPager(
            pagerState = pagerState
            // list = list
        )

        Box(
            modifier = Modifier
                .padding(end = 19.dp, bottom = 17.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
                .height(ViewPagerSliderNoticeBoardHeight)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .width(ViewPagerSliderNoticeBoardWidth)
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
        )*/ // 기존에 점으로 되어있는 코드 주석 양해 바랍니다 ㅎㅎ
    }
}

data class NoticeBoardData(
    val title: String,
    val time: String,
    val like: Boolean,
    val profile: String?,
    val imageList: List<String>?,
    val peopleList: List<String>?,
    val placeName: String,
    val contentText: String
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
fun PreviewNoticeBoard() {
    NoticeBoard(
        list = listOf(
            NoticeBoardData(
                "제목",
                "1시간 전",
                false,
                null,
                null,
                peopleList = null,
                placeName = "로쏘 (주) 전체 지점 ",
                contentText = "본문"
            )
        )
    )
}
