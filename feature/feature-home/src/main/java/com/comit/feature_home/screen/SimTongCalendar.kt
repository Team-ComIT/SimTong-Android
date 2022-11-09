package com.comit.feature_home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body6
import com.example.feature_home.R
import java.util.GregorianCalendar



@Stable
private fun Modifier.simTongCalendarTotalHeight() = height(420.dp)

@Stable
private val SimTongCalendarTotalRound = RoundedCornerShape(20.dp)

@Stable
private val SimTongCalendarTotalHorizontalPadding = PaddingValues(
    horizontal = 20.dp
)

@Composable
fun SimTongCalendar(

) {

    var year by remember { mutableStateOf("") }
    var month by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("") }

    val today = GregorianCalendar()



    Column(
        modifier = Modifier
            .fillMaxWidth()
            .simTongCalendarTotalHeight()
            .background(
                color = SimTongColor.Gray50,
                shape = SimTongCalendarTotalRound
            )
            .padding(SimTongCalendarTotalHorizontalPadding)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        SimTongCalendarDate(
            year = year,
            month = month,
            day = day,
            onBeforeClicked = {
            },
            onNextClicked = {}
        )
        
        Spacer(modifier = Modifier.height(37.dp))

        WeekTopRow()
    }
}

@Stable
private fun Modifier.simTongCalendarDateHeight() = height(30.dp)

@Stable
private fun Modifier.simTongCalendarDateButtonSize() = size(20.dp)

@Composable
fun SimTongCalendarDate(
    year: String,
    month: String,
    day: String,
    onBeforeClicked: () -> Unit,
    onNextClicked: () -> Unit
) {
    val year = year + stringResource(id = R.string.calendar_year)
    val month = month + stringResource(id = R.string.calendar_month)
    val day = day + stringResource(id = R.string.calendar_day)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .simTongCalendarDateHeight()
            .padding(start = 16.dp, end = 20.dp)
    ) {
        Body3(
            text = "$year $month $day",
            color = SimTongColor.Gray800
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.End)
                .width(60.dp)
        ) {
            IconButton(
                onClick = { onBeforeClicked() },
                modifier = Modifier
                    .simTongCalendarDateButtonSize()
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Calendar_Before.drawableId),
                    contentDescription = SimTongIcon.Calendar_Before.contentDescription
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            IconButton(
                onClick = { onNextClicked() },
                modifier = Modifier
                    .simTongCalendarDateButtonSize()
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Calendar_After.drawableId),
                    contentDescription = SimTongIcon.Calendar_After.contentDescription
                )
            }
        }
    }
}

@Composable
fun WeekTopRow(){

    val sunday: String = stringResource(id = R.string.calendar_sunday)
    val monday: String = stringResource(id = R.string.calendar_monday)
    val tuesday: String = stringResource(id = R.string.calendar_tuesday)
    val wednesday: String = stringResource(id = R.string.calendar_wednesday)
    val thursday: String = stringResource(id = R.string.calendar_thursday)
    val friday: String = stringResource(id = R.string.calendar_friday)
    val saturday: String = stringResource(id = R.string.calendar_saturday)

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp)
    ){
        val list = listOf(sunday,monday,tuesday,wednesday,thursday,friday,saturday)
        items(list){
            val textColor =
                if(it == stringResource(id = R.string.calendar_sunday)
                    || it == stringResource(id = R.string.calendar_saturday)) SimTongColor.Gray300
                else SimTongColor.Gray800
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth(1.toFloat()/7.toFloat())
            ) {
                Body6(
                    text = it,
                    color = textColor
                )
            }
        }
    }
}

@Stable
private fun Modifier.simTongCalendarItemSize() = size(40.dp)

@Stable
private fun Modifier.simTongCalendarItemBoxSize() = size(23.64.dp)

@Stable
private val SimTongCalendarItemBoxRound = RoundedCornerShape(9.09.dp)

@Composable
fun SimTongCalendarItem(
    day: String,
    workCount: Int,
    weekend: Boolean,
    thisMouth: Boolean,
    restDay: Boolean,
    annualDay: Boolean,
    onItemClicked: (() -> Unit)? = null
){
    val textColor =
        if(thisMouth) SimTongColor.Gray800
        else if (weekend) SimTongColor.Gray300
        else SimTongColor.Gray100

    val backgroundColor =
        if(restDay) SimTongColor.MainColor400
        else if(annualDay) SimTongColor.FocusBlue
        else SimTongColor.Gray200

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .simTongCalendarItemSize()
            .background(color = SimTongColor.Gray50)
    ){
        Body12(
            text = day,
            color = textColor
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .simTongCalendarItemBoxSize()
                .background(
                    color = backgroundColor,
                    shape = SimTongCalendarItemBoxRound
                )
        ) {
            if(restDay) {
                Body13(
                    text = stringResource(id = R.string.calendar_rest_day),
                    color = SimTongColor.White
                )
            }else if(annualDay) {
                Body13(
                    text = stringResource(id = R.string.calendar_annual_day),
                    color = SimTongColor.White
                )
            }else {
                Body11(
                    text = workCount.toString(),
                    color = SimTongColor.Gray400
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowCalendar(){
    SimTongCalendar()
}