package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body10
import com.comit.core_design_system.theme.Body6
import com.comit.core_design_system.theme.SimTongColor
import com.comit.core_design_system.theme.notoSansFamily
import java.text.DecimalFormat

data class CarrotMarketData(
    val productName: String,
    val place: String,
    val time: String,
    val price: Int,
    val like: Boolean
)

@Composable
fun CarrotMarketLazyVerticalGrid(
    modifier: Modifier = Modifier,
    list: List<CarrotMarketData>,
    imageHeight: Dp = 150.dp,
    heartClick: () -> Unit = {},
    cardClick: () -> Unit = {}
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(list) {
            CarrotMarketItemCard(
                modifier = modifier,
                data = it,
                imageHeight = imageHeight,
                heartClick = heartClick,
                cardClick = cardClick
            )
        }
    }
}

@Composable
fun CarrotMarketItemCard(
    modifier: Modifier,
    data: CarrotMarketData,
    imageHeight: Dp,
    heartClick: () -> Unit,
    cardClick: () -> Unit
) {

    val heartClickCheck = rememberSaveable { mutableStateOf(data.like) }

    Card(
        modifier = modifier
            .width(150.dp)
            .height(255.dp)
            .clickable { cardClick() }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(id = R.drawable.img_item_carrot),
                contentDescription = "item image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(imageHeight)
            )

            Body6(
                text = data.productName,
                color = SimTongColor.Black,
                modifier = Modifier.padding(20.dp, 15.dp, 0.dp, 0.dp)
            )

            Body10(
                text = data.place + " " + "•" + " " + data.time,
                color = SimTongColor.OtherColor.GrayA,
                modifier = Modifier.padding(20.dp, 3.dp)
            )

            val decimalFormat = DecimalFormat("#,###")
            Text(
                text = decimalFormat.format(data.price),
                color = SimTongColor.Black,
                fontSize = 15.sp,
                fontFamily = notoSansFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.Bottom)
                    .padding(20.dp, 0.dp, 0.dp, 25.dp)
            )
        }

        Image(
            painter = painterResource(
                id = SimTongIcons.Heart(heartClickCheck.value)
            ),
            contentDescription = "heart image",
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.Bottom)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .padding(0.dp, 0.dp, 20.dp, 25.dp)
                .noRippleClickable {
                    heartClickCheck.value = !heartClickCheck.value
                    heartClick()
                }
        )
    }
}

@Stable
private val CarrotMarketPrice: Int = 100000

@Preview
@Composable
fun CarrotMarket() {
    CarrotMarketLazyVerticalGrid(
        list = listOf(
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false
            )
        )
    )
}
