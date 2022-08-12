package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.icon.SimTongIcons
import com.comit.core_design_system.theme.Body10
import com.comit.core_design_system.theme.Body6
import com.comit.core_design_system.theme.notoSansFamily
import java.text.DecimalFormat

@Composable
fun CarrotLazyVerticalGrid(list: List<CarrotMarketData>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(list) {
            CarrotMarketItemCard(it)
        }
    }
}

@Composable
fun CarrotMarketItemCard(data: CarrotMarketData) {

    val heartClick = rememberSaveable { mutableStateOf(data.like) }

    Card(
        modifier = Modifier
            .width(150.dp)
            .height(255.dp)
            .clickable { }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Image(
                painterResource(id = R.drawable.img_item_carrot),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Body6(
                text = data.productName,
                modifier = Modifier.padding(20.dp, 5.dp, 0.dp, 0.dp)
            )

            Body10(
                text = data.place + " " + "•" + " " + data.time,
                modifier = Modifier.padding(20.dp, 3.dp)
            )

            val decimalFormat = DecimalFormat("#,###")
            Text(
                text = decimalFormat.format(data.price),
                color = Color.Black,
                fontSize = 15.sp,
                fontFamily = notoSansFamily,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentHeight(align = Alignment.Bottom)
                    .padding(20.dp, 0.dp, 0.dp, 25.dp)
            )
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.Bottom)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .padding(0.dp, 0.dp, 20.dp, 25.dp)
                .clickable { heartClick.value = !heartClick.value }
        ) {
            SimTongIcons.Heart(heartClick.value)
        }
    }
}

data class CarrotMarketData(
    val productName: String,
    val place: String,
    val time: String,
    val price: Int,
    val like: Boolean
)
