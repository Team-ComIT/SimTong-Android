package com.comit.core_design_system.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.*

data class VoteData(
    val title: String,
    val place: String,
    val time: String,
    val heartNum: Int,
    val commentNum: Int,
    val heartCheck: Boolean,
    val voteList: List<VoteListData>,
    val imageList: List<String?>?
)

data class VoteListData(
    val menu: String,
    val total: Int,
    val voteNum: Int,
    val check: Boolean
)


@Composable
fun VoteLazyColumn(
    list: List<VoteData>,
){
    LazyColumn(modifier = Modifier.background(SimTongColor.White)){
        items(list){
            VotePage(
                data = it,
            )
        }
    }
}

@Composable
fun VotePage(
    data: VoteData,
    onHeartClick: () -> Unit = {},
    onCommentClick: () -> Unit = {},
    onItemClick: (Int) -> Unit = {}
){

    Card(modifier = Modifier
        .fillMaxWidth()
        .background(SimTongColor.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(25.dp, 0.dp, 25.dp, 0.dp)
                .background(SimTongColor.White)
            ) {
                Body1(
                    text = data.title,
                    modifier = Modifier
                        .padding(0.dp,25.dp,0.dp,0.dp)
                )

                Body10(
                    text = data.place+" ・ "+data.time,
                    color = SimTongColor.Gray400,
                    modifier = Modifier
                        .padding(0.dp,0.dp,0.dp,20.dp)
                )

                VoteItemLazyColumn(
                    list = data.voteList,
                    onItemClick = onItemClick
                )

                Row(
                    modifier = Modifier
                        .padding(0.dp, 10.dp, 0.dp, 20.dp)
                        .fillMaxWidth()
                        .height(21.dp)
                ) {
                    TextHeart(
                        text = data.heartNum,
                        textStyle = SimTongTypography.body10,
                        textColor = SimTongColor.Black,
                        textModifier = Modifier
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically),
                        modifier = Modifier
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically),
                        onClick = onHeartClick,
                        click = data.heartCheck
                    )

                    Image(
                        painter = painterResource(
                            id = SimTongIcons.Comment),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(25.dp, 0.dp, 0.dp, 0.dp)
                            .clickable { onCommentClick() }
                    )

                    Body10(
                        text = data.commentNum.toString(),
                        color = SimTongColor.Black,
                        modifier = Modifier
                            .padding(5.dp, 0.dp, 0.dp, 0.dp)
                            .fillMaxHeight()
                            .wrapContentHeight(Alignment.CenterVertically)
                    )

                    PeopleImageList(
                        itemWidth = 23.dp,
                        nullPainter = painterResource(
                            id = R.drawable.img_notice_board_rectangle),
                        showImage = 2,
                        showListSize = true,
                        list = data.imageList,
                        paddingEnd = 18.0,
                        modifier = Modifier
                            .height(23.dp)
                    )
                }
            }
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(0.dp, 5.dp, 0.dp, 0.dp)
            ) {
                val canvasWidth = size.width
                val canvasHeight = size.height

                drawLine(
                    start = Offset(x = 0f, y = canvasHeight),
                    end = Offset(x = canvasWidth, y = canvasHeight),
                    color = OtherColor.GrayD8,
                    strokeWidth = 5f
                )
            }
        }
    }

}

@Composable
fun VoteItemLazyColumn(
    list: List<VoteListData>,
    onItemClick: (Int) -> Unit = {}
){
    Column {
        repeat(list.size){
            VoteItem(
                data = list[it],
                onItemClick = onItemClick,
                index = it
            )
        }
    }
}


@Composable()
fun VoteItem(
    data: VoteListData,
    onItemClick: (Int) -> Unit = {},
    index: Int
){

    val chooseState = rememberSaveable{ mutableStateOf(data.check) }
    val shapeColor : Color = if(chooseState.value) SimTongColor.MainColor else SimTongColor.Gray200
    val textColor : Color = if(chooseState.value) SimTongColor.White else SimTongColor.Black

    var total  = data.total
    val voteNum = rememberSaveable{ mutableStateOf(data.voteNum) }


    val animationProgress: Float by animateFloatAsState(
        targetValue = voteNum.value.toFloat() / total.toFloat(),
        tween(2000)
    )

    Box(modifier = Modifier
        .padding(0.dp, 0.dp, 0.dp, 10.dp)
        .fillMaxWidth()
        .height(32.dp)
        .background(
            color = SimTongColor.Gray100,
            shape = RoundedCornerShape(4.dp)
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
        Box(modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(animationProgress)
            .background(
                color = shapeColor,
                shape = RoundedCornerShape(4.dp)
            )
        )

        Body10(
            text = data.menu,
            color = textColor,
            modifier = Modifier
                .padding(10.dp, 0.dp, 0.dp, 0.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
        )

        Body12(
            text = voteNum.value.toString()+"명",
            color = shapeColor,
            modifier = Modifier
                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                .fillMaxHeight()
                .wrapContentHeight(Alignment.CenterVertically)
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
        )
    }

}

@Preview
@Composable
fun Vote(){

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
                ),
                VoteListData(
                    menu = "성심당",
                    total = 17,
                    voteNum = 5,
                    check = false
                ),
                VoteListData(
                    menu = "점심 ㄴ",
                    total = 17,
                    voteNum = 3,
                    check = true
                )
            ),
            imageList = listOf("1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2","1","2")
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
                ),
                VoteListData(
                    menu = "성심당",
                    total = 17,
                    voteNum = 5,
                    check = false
                ),
                VoteListData(
                    menu = "점심 ㄴ",
                    total = 17,
                    voteNum = 3,
                    check = true
                )
            ),
            imageList = listOf("1","2","3")
        )
    )

    VoteLazyColumn(list = list)
}