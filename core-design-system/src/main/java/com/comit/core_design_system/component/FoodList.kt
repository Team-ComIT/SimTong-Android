package com.comit.core_design_system.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.theme.Body9
import com.comit.core_design_system.theme.OtherColor
import com.comit.core_design_system.theme.SimTongColor
import com.comit.core_design_system.theme.notoSansFamily
import java.text.SimpleDateFormat
import java.util.*

data class FoodListDataSample(
    val time: String,
    val menu: String
)

@Composable
fun FoodListLazyRow(list: List<FoodListDataSample>) {
    LazyRow() {
        val currentTime = Calendar.getInstance().time
        val date = SimpleDateFormat("yyyy.MM.dd (EE)", Locale.KOREA).format(currentTime)

        items(list) {
            FoodListItem(foodListDataSample = it, date = date)
        }
    }
}

@Composable
fun FoodListItem(foodListDataSample: FoodListDataSample, date: String) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
            .border(
                width = 2.dp,
                color = SimTongColor.MainColor,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Card(
                shape = RoundedCornerShape(10.dp),
                backgroundColor = SimTongColor.MainColor,
                modifier = Modifier
                    .padding(10.dp, 13.dp, 0.dp, 0.dp)
            ) {
                Text(
                    text = foodListDataSample.time,
                    fontSize = 10.sp,
                    fontFamily = notoSansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .width(40.dp)
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                        .height(18.dp)
                        .wrapContentHeight(align = Alignment.CenterVertically)
                )
            }

            Body9(
                text = foodListDataSample.menu,
                modifier = Modifier
                    .height(96.dp)
                    .padding(10.dp, 10.dp, 10.dp, 0.dp)
            )
        }

        Text(
            text = date,
            color = OtherColor.GrayB3,
            fontSize = 9.sp,
            fontFamily = notoSansFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .fillMaxHeight()
                .wrapContentHeight(align = Alignment.Bottom)
                .fillMaxWidth()
                .wrapContentWidth(align = Alignment.End)
                .padding(0.dp, 0.dp, 10.dp, 7.dp)
        )
    }
}
