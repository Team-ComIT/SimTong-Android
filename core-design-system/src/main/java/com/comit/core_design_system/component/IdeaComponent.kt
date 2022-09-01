package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.icon.SimTongIcons.Heart
import com.comit.core_design_system.theme.*
import org.w3c.dom.Comment

data class IdeaData(
    val title: String,
    val body: String,
    val userName: String,
    val time: Int,
    val check: Boolean,
    val commentCount: Int
)
@Composable
fun IdeaLazyColumn(
    modifier: Modifier = Modifier,
    list: List<IdeaData>,
    onClickHeart: () -> Unit = {},
    onClickComment: () -> Unit = {}
){
    LazyColumn(){
        items(list){
            IdeaItem(
                modifier = modifier,
                data = it,
                onClickHeart = onClickHeart,
                onClickComment = onClickComment
            )
        }
    }
}

@Composable
fun IdeaItem(
    modifier: Modifier = Modifier,
    data: IdeaData,
    onClickHeart: ()-> Unit = {},
    onClickComment: () -> Unit = {}
){
    Column(
        modifier = modifier
    ) {

        Body6(
            text = data.title,
            modifier = Modifier
            .padding(30.dp,10.dp,0.dp,0.dp)
        )

        Body14(
            text = data.body,
            color = OtherColor.Gray96,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(30.dp, 3.dp, 0.dp, 0.dp)
        )

        Body14(
            text = data.userName+" • "+data.time+"분 전",
            color = OtherColor.Gray96,
            modifier = Modifier
                .padding(30.dp,5.dp,0.dp,0.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp, 10.dp, 0.dp, 0.dp)
                .height(14.dp)
        ) {

            TextHeart(
                text = "공감",
                textStyle = SimTongTypography.body14,
                textColor = OtherColor.Gray96,
                onClick = onClickHeart,
                isGray = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
            )

            Image(
                painter = painterResource(id = SimTongIcons.Comment(true)),
                contentDescription = "",
                modifier = Modifier
                    .padding(35.dp,0.dp,0.dp,0.dp)
                    .clickable { onClickComment() }
            )
            
            Body14(
                text = data.commentCount.toString(),
                color = OtherColor.Gray96,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
            )

        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .wrapContentHeight(Alignment.Bottom)
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

@Preview
@Composable
fun IdeaComponent(){
    IdeaLazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(91.dp)
            .background(SimTongColor.White),
        list = listOf(
            IdeaData("안녕하세요","안녕하세요,제 아이디어는 이번ㅇㄹ 구퍼ㅜㅕㄱㄷ루퍼두mvknejfncoenmdikneocmkdj ofekcmdn mkocemkn dkocem dk","유저 이름",5,false, 0),
            IdeaData("안녕하세요","안녕하세요,제 아이디어는 이번ㅇㄹ 구퍼ㅜㅕㄱㄷ루퍼두mvknejfncoenmdikneocmkdj ofekcmdn mkocemkn dkocem dk","유저 이름",5,false, 0)
        )
    )
}