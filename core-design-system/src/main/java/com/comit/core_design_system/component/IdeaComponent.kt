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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.R
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
    onHeartClicked: () -> Unit = {},
    onCommentClicked: () -> Unit = {},
    onItemClicked: () -> Unit = {}
) {
    LazyColumn() {
        items(list) {
            IdeaItem(
                modifier = modifier,
                data = it,
                onHeartClicked = onHeartClicked,
                onCommentClicked = onCommentClicked,
                onItemClicked = onItemClicked
            )
        }
    }
}

@Stable
private val IdeaItemHeight: Dp = 91.dp

@Stable
private val IdeaItemDataBodyWidth: Float = 0.5f

@Stable
private val IdeaItemRowHeight: Dp = 14.dp

@Stable
private val IdeaItemLineStartX: Float = 0f

@Stable
private val IdeaItemLineWidth: Float = 5F

@Composable
fun IdeaItem(
    modifier: Modifier = Modifier,
    data: IdeaData,
    onHeartClicked: () -> Unit = {},
    onCommentClicked: () -> Unit = {},
    onItemClicked: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(IdeaItemHeight)
            .background(SimTongColor.White)
            .noRippleClickable { onItemClicked() }
    ) {

        Body6(
            text = data.title,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp)
        )

        Body14(
            text = data.body,
            color = SimTongColor.OtherColor.Gray96,
            modifier = Modifier
                .fillMaxWidth(IdeaItemDataBodyWidth)
                .padding(start = 30.dp, top = 3.dp)
        )

        Body14(
            text = data.userName + " • " + data.time + "분 전",
            color = SimTongColor.OtherColor.Gray96,
            modifier = Modifier
                .padding(start = 30.dp, top = 5.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 10.dp)
                .height(IdeaItemRowHeight)
        ) {

            TextHeart(
                text = stringResource(id = R.string.sympathy),
                textStyle = SimTongTypography.body14,
                textColor = SimTongColor.OtherColor.Gray96,
                onClick = onHeartClicked,
                isGray = true,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(start = 10.dp)
            )

            Image(
                painter = painterResource(id = SimTongIcons.Comment(true)),
                contentDescription = stringResource(id = R.string.description_ic_comment),
                modifier = Modifier
                    .padding(start = 30.dp)
                    .noRippleClickable { onCommentClicked() }
            )

            Body14(
                text = data.commentCount.toString(),
                color = SimTongColor.OtherColor.Gray96,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(CenterVertically)
                    .padding(start = 10.dp)
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
                start = Offset(x = IdeaItemLineStartX, y = canvasHeight),
                end = Offset(x = canvasWidth, y = canvasHeight),
                color = SimTongColor.OtherColor.GrayD8,
                strokeWidth = IdeaItemLineWidth
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
