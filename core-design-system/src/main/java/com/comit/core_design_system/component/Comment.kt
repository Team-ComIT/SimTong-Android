package com.comit.core_design_system.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.noRippleClickable
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.Body9
import com.comit.core_design_system.typography.notoSansFamily

data class CommentData(
    val name: String,
    val content: String,
    val time: String,
    val likeNum: Int,
    val like: Boolean,
    val own: Boolean,
    val profileImage: String?
)

@ExperimentalFoundationApi
@Composable
fun Comment(
    modifier: Modifier = Modifier,
    list: List<CommentData>,
    onLongClicked: (Int) -> Unit = {},
    onCancelClicked: (Int) -> Unit = {},
    onLikeUpClicked: (Int) -> Unit = {},
    onLikeDownClicked: (Int) -> Unit = {},
    commentClick: (Int) -> Unit = {}
) {
    LazyColumn() {
        itemsIndexed(list) { index, data ->
            CommentItem(
                modifier = modifier,
                data = data,
                index = index,
                onLongClicked = onLongClicked,
                onCancelClicked = onCancelClicked,
                onLikeUpClicked = onLikeUpClicked,
                onLikeDownClicked = onLikeDownClicked,
                commentClick = commentClick
            )
        }
    }
}

@Stable
private val CommentItemCardHeight: Dp = 50.dp

@Stable
private val CommentItemImageSize: Dp = 32.dp

@Stable
private val CommentItemContentWidth: Dp = 120.dp

@Stable
private val CommentItemHeartSize: Dp = 10.dp

@ExperimentalFoundationApi
@Composable
fun CommentItem(
    modifier: Modifier,
    data: CommentData,
    index: Int,
    onLongClicked: (Int) -> Unit,
    onCancelClicked: (Int) -> Unit,
    onLikeUpClicked: (Int) -> Unit,
    onLikeDownClicked: (Int) -> Unit,
    commentClick: (Int) -> Unit
) {

    val like = rememberSaveable { mutableStateOf(data.like) }
    val likeNum = rememberSaveable { mutableStateOf(data.likeNum) }
    val likeText: String = stringResource(id = R.string.like) + " " + likeNum + stringResource(id = R.string.num)

    val onCLickCheck = rememberSaveable { mutableStateOf(false) }
    val backgroundColor = if (onCLickCheck.value) SimTongColor.OtherColor.RedFFE7E7 else SimTongColor.White

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(CommentItemCardHeight)
            .combinedClickable(
                onClick = {
                    onCancelClicked(index)
                    onCLickCheck.value = false
                },
                onLongClick = {
                    if (data.own) {
                        onLongClicked(index)
                        onCLickCheck.value = true
                    }
                }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
        ) {
            if (data.profileImage == null) {
                Image(
                    painter = painterResource(
                        id = SimTongIcon.Comment.drawableId // TODO("아이콘 체크 요함")
                    ),
                    contentDescription = stringResource(id = R.string.description_ic_profile),
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .width(CommentItemImageSize)
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .clip(CircleShape)
                )
            }

            Column(
                Modifier
                    .padding(
                        start = 10.dp,
                        top = 9.dp,
                        bottom = 9.dp
                    ),

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
                            .width(CommentItemContentWidth)
                            .padding(start = 10.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.Bottom)
                ) {

                    Body12(text = data.time)

                    Spacer(modifier = Modifier.width(15.dp))

                    Body12(text = likeText)

                    Spacer(modifier = Modifier.width(15.dp))

                    Body12(
                        text = stringResource(id = R.string.comment_write),
                        modifier = Modifier
                            .noRippleClickable { commentClick(index) }
                    )
                }
            }

            Image(
                painter = painterResource(
                    id = if (like.value) SimTongIcon.Heart_On.drawableId
                    else SimTongIcon.Heart_Off.drawableId,
                ),
                contentDescription = stringResource(id = R.string.like),
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .fillMaxHeight()
                    .wrapContentHeight(Alignment.CenterVertically)
                    .padding(end = 20.dp)
                    .height(CommentItemHeartSize)
                    .width(CommentItemHeartSize)
                    .noRippleClickable {
                        if (like.value) {
                            onLikeDownClicked(index)
                            likeNum.value--
                        } else {
                            onLikeUpClicked(index)
                            likeNum.value++
                        }
                        like.value = !like.value
                    }
            )
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun PreviewComment() {
    Comment(
        list = listOf(
            CommentData("장석연", "아이고..그런..", "1시간", 0, like = false, own = true, null),
            CommentData("장석연", "아이고..그런..사연이사연사연사연사연", "1시간", 0, like = false, own = true, null),
            CommentData("장석연", "아이고..그런..사연이..", "1시간", 0, false, own = true, null),
            CommentData("장석연", "아이고..그런..사연이..", "1시간", 0, false, own = false, null),
            CommentData("장석연", "아이고..그런..사연이..", "1시간", 0, false, own = false, null),
            CommentData("장석연", "아이고..그런..사연이..", "1시간", 0, false, own = false, null)
        )
    )
}
