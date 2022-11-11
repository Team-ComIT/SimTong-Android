package com.comit.feature_home.screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body12
import com.comit.core_design_system.typography.Body13
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body6
import com.example.feature_home.R
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.Month
import java.util.Calendar
import java.util.GregorianCalendar

data class SimTongCalendarData(
    val day: String,
    val workCount: Int,
    val weekend: Boolean,
    val thisMouth: Boolean,
    val restDay: Boolean,
    val annualDay: Boolean
)

@Stable
private val SimTongCalendarTotalHeight: Dp = 420.dp

@Stable
private val SimTongCalendarTotalRound = RoundedCornerShape(20.dp)

@Stable
private val SimTongCalendarTotalHorizontalPadding = PaddingValues(
    horizontal = 20.dp
)

@Composable
fun SimTongCalendar(

) {
    var checkMonth by remember { mutableStateOf(0) }

    val today = GregorianCalendar()
    var calendar = GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH),1)

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf(calendar.get(Calendar.MONTH)+1) }
    var day by remember { mutableStateOf(calendar.get(Calendar.DATE)) }

    var calendarList by remember { mutableStateOf(organizeList(1)) }

    Column(
        modifier = Modifier
            .padding(SimTongCalendarTotalHorizontalPadding)
            .fillMaxWidth()
            .height(SimTongCalendarTotalHeight)
            .background(
                color = SimTongColor.Gray50,
                shape = SimTongCalendarTotalRound
            )
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        SimTongCalendarDate(
            year = year.toString(),
            month = month.toString(),
            day = day.toString(),
            onBeforeClicked = {
                checkMonth --
                calendar = GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+checkMonth,1)
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH) + 1
            },
            onNextClicked = {
                checkMonth ++
                calendar = GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+checkMonth,1)
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH) + 1
            }
        )
        
        Spacer(modifier = Modifier.height(37.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 14.dp)
                .fillMaxWidth()
        ) {
            WeekTopRow()

            SimTongCalendarList(
                list = calendarList
            ) { day = returnDay(day.toString()) }
        }
    }
}

@Stable
private val SimTongCalendarDateHeight: Dp = 30.dp

@Stable
private val SimTongCalendarDateButtonSize:Dp = 20.dp

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
            .padding(start = 16.dp, end = 20.dp)
            .fillMaxWidth()
            .height(SimTongCalendarDateHeight)
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
                    .size(SimTongCalendarDateButtonSize)
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
                    .size(SimTongCalendarDateButtonSize)
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
        modifier = Modifier.fillMaxWidth()
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

@Composable
fun SimTongCalendarList(
    list: List<SimTongCalendarData>,
    onItemClicked: (String) -> Unit
){


//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        repeat(list.size/7){
//
//        }
//    }
//    LazyRow(
//        modifier = Modifier
//            .fillMaxWidth()
//    ) {
//        items(list){
//            Log.d("TAG", "SimTongCalendarList: $date")
//
//            SimTongCalendarItem(
//                day = list[date].day,
//                workCount = list[date].workCount,
//                weekend = list[date].weekend,
//                thisMouth = list[date].thisMouth,
//                restDay = list[date].restDay,
//                annualDay = list[date].annualDay,
//                onItemClicked = onItemClicked,
//                modifier = Modifier
//                    .fillParentMaxSize(1.toFloat()/7.toFloat())
//            )
//
//            date++
//        }
//    }
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(list){
            SimTongCalendarItem(
                day = it.day,
                workCount = it.workCount,
                weekend = it.weekend,
                thisMouth = it.thisMouth,
                restDay = it.restDay,
                annualDay = it.annualDay,
                onItemClicked = onItemClicked,
                modifier = Modifier
                    .fillParentMaxSize(1.toFloat()/7.toFloat())
            )
        }
    }

}

@Stable
private val SimTongCalendarItemSize: Dp = 40.dp

@Stable
private val SimTongCalendarItemBoxSize: Dp = 23.64.dp

@Stable
private val SimTongCalendarItemBoxRound = RoundedCornerShape(9.09.dp)

@Composable
fun SimTongCalendarItem(
    modifier: Modifier = Modifier,
    day: String,
    workCount: Int,
    weekend: Boolean,
    thisMouth: Boolean,
    restDay: Boolean,
    annualDay: Boolean,
    onItemClicked: (String) -> Unit
){
    val textColor =
        if(weekend) SimTongColor.Gray300
        else if (thisMouth) SimTongColor.Gray800
        else SimTongColor.Gray100

    val backgroundColor =
        if(restDay) SimTongColor.MainColor400
        else if(annualDay) SimTongColor.FocusBlue
        else SimTongColor.Gray200

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.noRippleClickable { onItemClicked(day) }
    ){
        Body12(
            text = day,
            color = textColor
        )

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(SimTongCalendarItemBoxSize)
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
            }else if(thisMouth){
                Body11(
                    text = workCount.toString(),
                    color = SimTongColor.Gray400
                )
            }
        }
    }
}

private fun returnDay(day: String): Int = day.toInt()

private fun organizeList(
    checkMonth: Int
): ArrayList<SimTongCalendarData> {
    val calendarList = ArrayList<SimTongCalendarData>()
    val today = GregorianCalendar()
    val calendar = GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+checkMonth,1)
    val min = calendar.get(Calendar.DAY_OF_WEEK)-1
    val max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val year = calendar.get(Calendar.YEAR)
    val mouth = calendar.get(Calendar.MONTH)+1

    val lastCalendar = GregorianCalendar(today.get(Calendar.YEAR),today.get(Calendar.MONTH)+checkMonth-1,1)
    val lastMax = lastCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    for(i in min-1 downTo  0){
        calendarList.add(
            SimTongCalendarData(
                day = (lastMax-i).toString(),
                workCount = 0,
                weekend = false,
                thisMouth = false,
                restDay = false,
                annualDay = false
            )
        )

        Log.d("TAG", "before: "+(lastMax-i))
    }

    for (i in 1..max){

        val dayOfWeek = LocalDate.of(year,mouth,i).dayOfWeek.value
        val weekend = dayOfWeek == 6 || dayOfWeek == 7

        calendarList.add(
            SimTongCalendarData(
                day = i.toString(),
                workCount = 0,
                weekend = weekend,
                thisMouth = true,
                restDay = false,
                annualDay = false
            )
        )

        Log.d("TAG", "this: $i")
    }

    for(i in 1..7){
        if(calendarList.size%7 == 0){
            Log.d("TAG", "break: ")
            break
        } else {
            calendarList.add(
                SimTongCalendarData(
                    day = i.toString(),
                    workCount = 0,
                    weekend = false,
                    thisMouth = false,
                    restDay = false,
                    annualDay = false
                )
            )
            Log.d("TAG", "next: $i")
        }
    }

    return calendarList
}



@Preview(showBackground = true)
@Composable
fun ShowCalendar(){
    Column(modifier = Modifier.fillMaxSize()) {
        SimTongCalendar()
    }
}