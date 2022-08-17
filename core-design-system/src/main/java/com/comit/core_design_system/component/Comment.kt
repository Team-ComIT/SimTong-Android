package com.comit.core_design_system.component

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body9
import com.comit.core_design_system.theme.OtherColor
import com.comit.core_design_system.theme.SimTongColor
import com.comit.core_design_system.theme.notoSansFamily

data class CommentData(
    val name: String,
    val content: String,
    val time : String,
    val likeNum: Int,
    val like: Boolean
)

@ExperimentalFoundationApi
@Composable
fun CommentItemLazyColumn(
    list: List<CommentData>,
    longClick: (Int) -> Unit = {},
    onCLickCancel: (Int) -> Unit = {}
){
    LazyColumn(){
        itemsIndexed(list){index, it->
            CommentItem(it, index, longClick, onCLickCancel)
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun CommentItem(
    data: CommentData,
    index: Int,
    longClick: (Int) -> Unit = {},
    onCLickCancel: (Int) -> Unit = {}
){

    val like = rememberSaveable { mutableStateOf(data.like) }

    val onCLickCheck = rememberSaveable{ mutableStateOf(false) }
    val backgroundColor = if(onCLickCheck.value) OtherColor.RedFFE7E7 else SimTongColor.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .combinedClickable(
                onClick = {
                    onCLickCancel(index)
                    onCLickCheck.value = false
                },
                onLongClick = {
                    longClick(index)
                    onCLickCheck.value = true
                }
            )
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
        ) {
            Image(painter = painterResource(
                id = R.drawable.img_notice_board_rectangle),
                contentDescription = "",
                modifier = Modifier
                    .padding(20.dp, 0.dp, 0.dp, 0.dp)
                    .width(32.dp)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .clip(CircleShape)
            )

            Column(
                Modifier
                .padding(10.dp,9.dp,0.dp,9.dp)
            ) {
                Row {
                    Body9(
                        text = data.name,
                        color = SimTongColor.Black
                    )

                    Text(
                        text = data.content,
                        fontSize = 11.sp,
                        fontFamily = notoSansFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .width(120.dp)
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    )
                }

                Row(modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.Bottom)
                ) {

                    CommentText(text = data.time)

                    CommentText(
                        text = "좋아요 "+data.likeNum+"개",
                        modifier = Modifier
                            .padding(15.dp,0.dp,0.dp,0.dp)
                    )

                    CommentText(
                        text = "댓글 쓰기",
                        modifier = Modifier
                            .padding(15.dp,0.dp,0.dp,0.dp)
                    )
                }
            }

            Image(
                painter = painterResource(
                    id = SimTongIcons.Heart(like.value)),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(0.dp, 0.dp, 20.dp, 0.dp)
                    .height(10.dp)
                    .width(10.dp)
                    .clickable { like.value = !like.value }
            )
        }

    }
}

@Composable
fun CommentText(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        fontFamily = notoSansFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        color = OtherColor.Black34,
        modifier = modifier
    )
}

@ExperimentalFoundationApi
@Preview
@Composable
fun Comment(){
    CommentItemLazyColumn(
        list = listOf(
            CommentData("장석연","아이고..그런..","1시간",0,false),
            CommentData("장석연","아이고..그런..사연이사연사연사연사연","1시간",0,false),
            CommentData("장석연","아이고..그런..사연이..","1시간",0,false),
            CommentData("장석연","아이고..그런..사연이..","1시간",0,false),
            CommentData("장석연","아이고..그런..사연이..","1시간",0,false),
            CommentData("장석연","아이고..그런..사연이..","1시간",0,false)
            )
    )
}