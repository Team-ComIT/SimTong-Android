package com.comit.core_design_system.component

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.util.currentMealsTime

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

@Stable
private val FoodListItemCardWidth: Dp = 140.dp

@Stable
private val FoodListItemCardHeight: Dp = 160.dp

@Stable
private val FoodListItemRound: Dp = 10.dp

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
        shape = RoundedCornerShape(FoodListItemRound),
        backgroundColor = SimTongColor.Transparent,
        elevation = 0.dp,
        modifier = modifier
            .width(FoodListItemCardWidth)
            .height(FoodListItemCardHeight)
            .padding(horizontal = 15.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .paint(painterResource(id = backgroundImage)),
        ) {

            Body13(
                text = menu,
                color = textColor,
                line,
                modifier = Modifier
                    .padding(
                        start = 12.dp,
                        top = 15.dp,
                        end = 10.dp
                    )
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
        timeCheck = currentMealsTime(),
        list = listOf(
            "누룽지\n돼지불고기\n마늘쫑건새우볶음\n배추김치\n달콤한붓세빵\n바나나/딸기우유",
            "돼지불고기",
            "배추김치"
        ),
    )
}
