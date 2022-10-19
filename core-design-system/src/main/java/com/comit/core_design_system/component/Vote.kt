package com.comit.core_design_system.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
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
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.SimTongTypography

data class VoteData(
    val title: String,
    val place: String,
    val time: String,
    val heartNum: Int,
    val commentNum: Int,
    val heartCheck: Boolean,
    val voteList: List<VoteListData>,
    val imageList: List<String?>?,
)

data class VoteListData(
    val menu: String,
    val total: Int,
    val voteNum: Int,
    val check: Boolean,
)

@Composable
fun Vote(
    modifier: Modifier = Modifier,
    list: List<VoteData>,
    onHeartClicked: () -> Unit = {},
    onCommentClicked: () -> Unit = {},
    onItemClicked: (Int) -> Unit = {},
) {
    LazyColumn() {
        items(list) {
            VotePage(
                modifier = modifier,
                data = it,
                onHeartClicked = onHeartClicked,
                onCommentClicked = onCommentClicked,
                onItemClicked = onItemClicked,
            )
        }
    }
}

@Stable
private val VotePageBottomRowHeight: Dp = 21.dp

@Stable
private val PeopleImageListWidth: Dp = 23.dp

@Stable
private val PeopleImageListCount: Int = 2

@Stable
private val PeopleImageListPaddingEnd: Double = 18.0

@Stable
private val PeopleImageListHeight: Dp = 23.dp

@Stable
private val VotePageLineStartX: Float = 0f

@Stable
private val VotePageLineWidth: Float = 5f

@Composable
fun VotePage(
    modifier: Modifier,
    data: VoteData,
    onHeartClicked: () -> Unit,
    onCommentClicked: () -> Unit,
    onItemClicked: (Int) -> Unit,
) {

    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 25.dp)
                    .background(SimTongColor.White),
            ) {

                Body1(
                    text = data.title,
                    modifier = Modifier
                        .padding(top = 20.dp)
                )

                Body10(
                    text = data.place + " ・ " + data.time,
                    color = SimTongColor.Gray400,
                    modifier = Modifier
                        .padding(top = 4.dp, bottom = 20.dp)
                )

                VoteItemLazyColumn(
                    list = data.voteList,
                    onItemClick = onItemClicked,
                )

                Row(
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 20.dp)
                        .fillMaxWidth()
                        .height(VotePageBottomRowHeight)
                ) {

                    TextHeart2(
                        text = data.heartNum,
                        textStyle = SimTongTypography.body10,
                        textColor = SimTongColor.Black,
                        textModifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically),
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically),
                        onClick = onHeartClicked,
                        click = data.heartCheck,
                    )

                    Image(
                        painter = painterResource(
                            id = SimTongIcon.Comment.drawableId,
                        ),
                        contentDescription = stringResource(id = R.string.description_ic_comment),
                        modifier = Modifier
                            .padding(start = 25.dp)
                            .clickable { onCommentClicked() }
                    )

                    Body10(
                        text = data.commentNum.toString(),
                        color = SimTongColor.Black,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                    )

                    PeopleImageList(
                        itemWidth = PeopleImageListWidth,
                        nullPainter = painterResource(
                            id = R.drawable.img_notice_board_rectangle
                        ),
                        showImage = PeopleImageListCount,
                        showListSize = true,
                        list = data.imageList,
                        paddingEnd = PeopleImageListPaddingEnd,
                        modifier = Modifier
                            .height(PeopleImageListHeight),
                    )
                }
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(top = 5.dp)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawLine(
                    start = Offset(x = VotePageLineStartX, y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = SimTongColor.OtherColor.GrayD8,
                    strokeWidth = VotePageLineWidth,
                )
            }
        }
    }
}

@Composable
fun VoteItemLazyColumn(
    list: List<VoteListData>,
    onItemClick: (Int) -> Unit = {}
) {
    Column {
        repeat(list.size) {
            VoteItem(
                data = list[it],
                onItemClick = onItemClick,
                index = it,
            )
        }
    }
}

@Stable
private val VoteItemAnimationProgressTween: Int = 2000

@Stable
private val VoteItemHeight: Dp = 32.dp

@Stable
private val VoteItemRound: Dp = 4.dp

@Composable
fun VoteItem(
    data: VoteListData,
    onItemClick: (Int) -> Unit = {},
    index: Int
) {

    val chooseState = rememberSaveable { mutableStateOf(data.check) }
    val shapeColor: Color = if (chooseState.value) SimTongColor.MainColor else SimTongColor.Gray200
    val textColor: Color = if (chooseState.value) SimTongColor.White else SimTongColor.Black
    val textNumColor: Color =
        if (chooseState.value) SimTongColor.MainColor else SimTongColor.Gray600

    var total = data.total
    val voteNum = rememberSaveable { mutableStateOf(data.voteNum) }

    val animationProgress: Float by animateFloatAsState(
        targetValue = voteNum.value.toFloat() / total.toFloat(),
        tween(VoteItemAnimationProgressTween)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(VoteItemHeight)
            .background(
                color = SimTongColor.Gray100,
                shape = RoundedCornerShape(VoteItemRound),
            )
            .clickable {
                chooseState.value = !chooseState.value
                if (chooseState.value) {
                    total++
                    voteNum.value++
                } else {
                    total--
                    voteNum.value--
                }
                onItemClick(index)
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(animationProgress)
                .background(
                    color = shapeColor,
                    shape = RoundedCornerShape(VoteItemRound)
                ),
        )

        Body10(
            text = data.menu,
            color = textColor,
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically),
        )

        Body12(
            text = voteNum.value.toString() + stringResource(id = R.string.number_of_people),
            color = textNumColor,
            modifier = Modifier
                .padding(end = 10.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End),
        )
    }

    Spacer(modifier = Modifier.height(10.dp))
}

@Preview
@Composable
fun PreviewVote() {

    val list = listOf(
        VoteData(
            title = "점심 메뉴 투표",
            place = "전체 지점 로쏘(주)",
            time = "2시간 전",
            heartNum = 17,
            commentNum = 17,
            heartCheck = true,
            voteList = listOf(
                VoteListData(
                    menu = "아보카도",
                    total = 17,
                    voteNum = 1,
                    check = false
                ),
                VoteListData(
                    menu = "쑤시",
                    total = 17,
                    voteNum = 8,
                    check = false
                )
            ),
            imageList = null
        ),
        VoteData(
            title = "점심 메뉴 투표",
            place = "전체 지점 로쏘(주)",
            time = "2시간 전",
            heartNum = 17,
            commentNum = 17,
            heartCheck = true,
            voteList = listOf(
                VoteListData(
                    menu = "아보카도",
                    total = 17,
                    voteNum = 1,
                    check = false
                ),
                VoteListData(
                    menu = "쑤시",
                    total = 17,
                    voteNum = 8,
                    check = false
                )
            ),
            imageList = listOf("1", "2", "3"),
        )
    )

    Vote(
        list = list
    )
}
