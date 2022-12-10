package com.comit.core_design_system.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.R
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body4
import com.comit.core_design_system.typography.Body7

data class Meal(
    val date: String,
    val food: List<String>
)

@Composable
fun MealList(
    meals: List<Meal>,
) {
    val state = rememberLazyListState(
        initialFirstVisibleItemIndex = 3,
        initialFirstVisibleItemScrollOffset = -60
    )

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = state,
    ) {
        itemsIndexed(meals) { index, meal ->
            MeaContent(
                date = meal.date,
                meals = meal.food,
                isCurrentDay = index == 3,
            )
        }
    }
}

@Composable
fun MeaContent(
    date: String,
    meals: List<String>,
    isCurrentDay: Boolean,
) {
    Box(
        modifier = Modifier.size(111.dp, 192.dp)
    ) {
        Image(
            painter = painterResource(
                id = if (isCurrentDay) R.drawable.img_meal_red else R.drawable.img_meal_white,
            ),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier.padding(14.dp),
        ) {
            Body14(
                text = date,
                color = if (isCurrentDay) SimTongColor.White else SimTongColor.Gray800,
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(meals) {
                    Body13(
                        text = it,
                        color = if (isCurrentDay) SimTongColor.White else SimTongColor.Gray800,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewMealListVersion2() {
    MealList(
        meals = listOf(
            Meal("12월 1일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 2일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 3일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 4일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 5일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 6일", listOf("1", "2", "3", "4", "5", "6")),
            Meal("12월 7일", listOf("1", "2", "3", "4", "5", "6")),
        )
    )
}