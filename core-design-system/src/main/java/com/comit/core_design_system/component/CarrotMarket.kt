package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body10
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body6
import java.text.DecimalFormat

data class CarrotMarketData(
    val productName: String,
    val place: String,
    val time: String,
    val price: Int,
    val like: Boolean,
    val imageUrl: String?
)

@Composable
fun CarrotMarket(
    modifier: Modifier = Modifier,
    list: List<CarrotMarketData>,
    onHeartClicked: () -> Unit = {},
    onCardClicked: () -> Unit = {}
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(list) {
            CarrotMarketItemCard(
                modifier = modifier,
                data = it,
                onHeartClicked = onHeartClicked,
                onCardClicked = onCardClicked
            )
        }
    }
}

@Stable
private val CarrotMarketItemCardRoundedCornerShape = 5.dp

@Stable
private val CarrotMarketItemCardImagePadding = 50.dp

@Composable
fun CarrotMarketItemCard(
    modifier: Modifier,
    data: CarrotMarketData,
    onHeartClicked: () -> Unit,
    onCardClicked: () -> Unit
) {

    val heartClickCheck = rememberSaveable { mutableStateOf(data.like) }

    Card(
        modifier = modifier
            .clickable { onCardClicked() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {

            Box(
                modifier = Modifier
                    .background(
                        color = SimTongColor.Gray200,
                        shape = RoundedCornerShape(CarrotMarketItemCardRoundedCornerShape)
                    )
            ) {
                if (data.imageUrl == null) {
                    Image(
                        painterResource(id = R.drawable.ic_carrot_market_base),
                        contentDescription = stringResource(id = R.string.description_ic_item),
                        modifier = Modifier
                            .padding(CarrotMarketItemCardImagePadding)
                    )
                }
            }

            Body6(
                text = data.productName,
                color = SimTongColor.Black,
                modifier = Modifier.padding(top = 10.dp)
            )

            Body10(
                text = data.place + " " + "•" + " " + data.time,
                color = SimTongColor.OtherColor.GrayA,
                modifier = Modifier.padding(top = 2.dp, bottom = 9.dp)
            )

            Row(
                modifier = Modifier
                    .height(21.dp)
            ) {
                val decimalFormat = DecimalFormat("#,###")

                Body3(
                    text = decimalFormat.format(data.price),
                    color = SimTongColor.Black,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                )

                Image(
                    painter = painterResource(
                        id = if (heartClickCheck.value) SimTongIcon.Heart_On.drawableId
                        else SimTongIcon.Heart_Off.drawableId
                    ),
                    contentDescription = stringResource(id = R.string.description_ic_heart),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.End)
                        .noRippleClickable {
                            heartClickCheck.value = !heartClickCheck.value
                            onHeartClicked()
                        }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Stable
private val CarrotMarketPrice: Int = 100000

@Preview
@Composable
fun PreviewCarrotMarket() {
    CarrotMarket(
        list = listOf(
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false,
                null
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false,
                null
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false,
                null
            ),
            CarrotMarketData(
                "제품",
                "본점",
                "1시간 전",
                CarrotMarketPrice,
                false,
                null
            )
        )
    )
}
