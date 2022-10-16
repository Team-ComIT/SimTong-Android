package com.comit.core_design_system.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body6
import com.comit.core_design_system.typography.SimTongTypography

data class IdeaData(
    val title: String,
    val body: String,
    val userName: String,
    val time: Int,
    val check: Boolean,
    val commentCount: Int
)
@Composable
fun IdeaComponent(
    modifier: Modifier = Modifier,
    list: List<IdeaData>,
    onClickHeart: () -> Unit = {},
    onClickComment: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    LazyColumn() {
        items(list) {
            IdeaItem(
                modifier = modifier,
                data = it,
                onClickHeart = onClickHeart,
                onClickComment = onClickComment,
                onItemClick = onItemClick
            )
        }
    }
}

@Stable
private val IdeaItemDataBodyWidth: Float = 0.5f

@Composable
fun IdeaItem(
    modifier: Modifier = Modifier,
    data: IdeaData,
    onClickHeart: () -> Unit = {},
    onClickComment: () -> Unit = {},
    onItemClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(91.dp)
            .background(SimTongColor.White)
            .noRippleClickable { onItemClick() }
    ) {

        Body6(
            text = data.title,
            modifier = Modifier
                .padding(30.dp, 10.dp, 0.dp, 0.dp)
        )

        Body14(
            text = data.body,
            color = SimTongColor.OtherColor.Gray96,
            modifier = Modifier
                .fillMaxWidth(IdeaItemDataBodyWidth)
                .padding(30.dp, 3.dp, 0.dp, 0.dp)
        )

        Body14(
            text = data.userName + " • " + data.time + "분 전",
            color = SimTongColor.OtherColor.Gray96,
            modifier = Modifier
                .padding(30.dp, 5.dp, 0.dp, 0.dp)
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
                textColor = SimTongColor.OtherColor.Gray96,
                onClick = onClickHeart,
                isGray = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
            )

            Image(
                painter = painterResource(id = SimTongIcons.Comment(true)),
                contentDescription = "comment image",
                modifier = Modifier
                    .padding(30.dp, 0.dp, 0.dp, 0.dp)
                    .noRippleClickable { onClickComment() }
            )

            Body14(
                text = data.commentCount.toString(),
                color = SimTongColor.OtherColor.Gray96,
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
                color = SimTongColor.OtherColor.GrayD8,
                strokeWidth = 5f
            )
        }
    }
}

@Stable
private val PreViewTime: Int = 5

@Stable
private val PreViewCommentCount: Int = 0

@Preview
@Composable
fun PreviewIdeaComponent() {
    IdeaComponent(
        list = listOf(
            IdeaData(
                "안녕하세요",
                "안녕하세요,제 아이디어는 이번ㅇㄹ 구퍼ㅜㅕㄱㄷ루퍼두mvknejfncoenmdikneocmkdj ofekcmdn mkocemkn dkocem dk",
                "유저 이름",
                PreViewTime,
                false,
                PreViewCommentCount
            ),
            IdeaData(
                "안녕하세요",
                "안녕하세요,제 아이디어는 이번ㅇㄹ 구퍼ㅜㅕㄱㄷ루퍼두mvknejfncoenmdikneocmkdj ofekcmdn mkocemkn dkocem dk",
                "유저 이름",
                PreViewTime,
                true,
                PreViewCommentCount
            )
        )
    )
}
