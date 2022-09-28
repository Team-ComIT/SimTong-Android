package com.comit.core_design_system.component

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.theme.Body13
import com.comit.core_design_system.theme.SimTongColor
import java.sql.Date
import java.text.SimpleDateFormat

@Composable
fun FoodList(
    modifier: Modifier = Modifier,
    textColorBase: Color = SimTongColor.Black,
    textColorCheck: Color = SimTongColor.White,
    background: Int = R.drawable.img_food,
    backgroundCheck: Int = R.drawable.img_food_red,
    timeCheck: Int,
    list: List<String>,
) {

    LazyRow {
        itemsIndexed(list) { index, data ->
            FoodListItem(
                modifier = modifier,
                index = index,
                menu = data,
                timeCheck = timeCheck,
                textColorBase = textColorBase,
                textColorCheck = textColorCheck,
                background = background,
                backgroundCheck = backgroundCheck
            )
        }
    }
}

@Composable
fun FoodListItem(
    modifier: Modifier = Modifier,
    index: Int,
    menu: String,
    timeCheck: Int,
    textColorBase: Color,
    textColorCheck: Color,
    background: Int,
    backgroundCheck: Int
) {

    val textColor: Color =
        if (timeCheck == index) textColorCheck else textColorBase
    val backgroundImage: Int =
        if (timeCheck == index) backgroundCheck else background

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = SimTongColor.Transparent,
        elevation = 0.dp,
        modifier = modifier
            .width(140.dp)
            .height(160.dp)
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(painterResource(id = backgroundImage))
        ) {

            Body13(
                text = menu,
                color = textColor,
                lineHeight = 23.sp,
                modifier = Modifier
                    .padding(12.dp, 15.dp, 10.dp, 0.dp)
            )
        }
    }
}

@Preview
@Composable
fun FoodListPreview() {

    FoodList(
        textColorBase = SimTongColor.Black,
        textColorCheck = SimTongColor.White,
        timeCheck = time(),
        list = listOf(
            "누룽지\n돼지불고기\n마늘쫑건새우볶음\n배추김치\n달콤한붓세빵\n바나나/딸기우유",
            "돼지불고기",
            "배추김치"
        )
    )
}

@Stable
private val Time900: Int = 900

@Stable
private val Time1700: Int = 1700

@SuppressLint("SimpleDateFormat")
fun time(): Int {
    val time = SimpleDateFormat("HHmm").format(Date(System.currentTimeMillis())).toInt()
    Log.d("TAG", "time: $time")

    var timeCheck = 0

    if (time > Time1700) timeCheck = 2

    else if (time > Time900) { timeCheck = 1 }

    return timeCheck
}
