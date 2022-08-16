package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.comit.core_design_system.R
import com.comit.core_design_system.theme.*
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun FoodListLazyRow(list: List<String>) {
    val time = SimpleDateFormat("hhmm").format(Date(System.currentTimeMillis())).toInt()
    var timeCheck = 0

    if(time > 1500){
        timeCheck = 2
    }
    else if(time > 1000){
        timeCheck = 1
    }

    LazyRow() {
        itemsIndexed(list) {index,it->
            FoodListItem(index, it, timeCheck)
        }
    }
}

@Composable
fun FoodListItem(index: Int, menu: String, timeCheck: Int) {

    val textColor: Color = if(timeCheck == index) SimTongColor.White else SimTongColor.Black
    val backgroundImage: Int = if(timeCheck == index) R.drawable.img_food_red else R.drawable.img_food

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFF00FF0000),
        modifier = Modifier
            .width(140.dp)
            .height(160.dp)
            .padding(15.dp, 0.dp, 15.dp, 0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()){
            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
            )

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
fun FoodList(){
    FoodListLazyRow(list = listOf(
        "누룽지\n돼지불고기\n마늘쫑건새우볶음\n배추김치\n달콤한붓세빵\n바나나/딸기우유",
        "돼지불고기",
        "배추김치")
    )
}
