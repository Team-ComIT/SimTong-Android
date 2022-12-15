package com.comit.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.comit.common.WeekOfDay.Friday
import com.comit.common.WeekOfDay.Monday
import com.comit.common.WeekOfDay.Saturday
import com.comit.common.WeekOfDay.Sunday
import com.comit.common.WeekOfDay.Thursday
import com.comit.common.WeekOfDay.Tuesday
import com.comit.common.WeekOfDay.Wednesday
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body14
import com.comit.core_design_system.typography.Body3
import java.util.GregorianCalendar
import java.util.Calendar


@Stable
private val DialogSize: Dp = 320.dp

@Stable
private val DialogShape: Dp = 10.dp

@Stable
private val SimTongCalendarDateButtonSize: Dp = 20.dp

data class SimTongCalendarDialogData(
    val day: String,
    val weekend: Boolean,
    val thisMonth: Boolean,
)

@Composable
fun SimTongCalendarDialog(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onDismissRequest: () -> Unit,
    startDay: Int = 0,
    afterDay: Int = 0,
    setStartDay: Boolean = false,
    setEndDay: Boolean = false,
    onItemClicked: () -> Unit,
    properties: DialogProperties = DialogProperties(),
) {
    var checkMonth by remember { mutableStateOf(0) }

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DATE)
    )

    var year by remember { mutableStateOf(calendar.get(Calendar.YEAR)) }
    var month by remember { mutableStateOf((calendar.get(Calendar.MONTH) + 1)) }

    if (visible) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = properties,
        ) {
            Column(
                modifier = modifier
                    .size(DialogSize)
                    .background(
                        color = SimTongColor.White,
                        shape = RoundedCornerShape(DialogShape),
                    )
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = 16.dp, top = 18.dp, end = 20.dp)
                        .fillMaxWidth()
                ) {
                    val yearT = year.toString() + "년 "
                    val monthT = month.toString() + "월"
                    Body3(
                        text = yearT + monthT,
                        color = SimTongColor.Gray800
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                            .width(60.dp)
                    ) {
                        IconButton(
                            onClick = {
                                checkMonth --
                                val calendar = GregorianCalendar(
                                    today.get(Calendar.YEAR),
                                    today.get(Calendar.MONTH) + checkMonth,
                                    today.get(Calendar.DATE)
                                )
                                year = calendar.get(Calendar.YEAR)
                                month = (calendar.get(Calendar.MONTH) + 1)
                            },
                            modifier = Modifier.size(SimTongCalendarDateButtonSize)
                        ) {
                            Icon(
                                painter = painterResource(id = SimTongIcon.Calendar_Before.drawableId),
                                contentDescription = SimTongIcon.Calendar_Before.contentDescription
                            )
                        }

                        Spacer(modifier = Modifier.width(20.dp))

                        IconButton(
                            onClick = {
                                checkMonth ++
                                val calendar = GregorianCalendar(
                                    today.get(Calendar.YEAR),
                                    today.get(Calendar.MONTH) + checkMonth,
                                    today.get(Calendar.DATE)
                                )
                                year = calendar.get(Calendar.YEAR)
                                month = (calendar.get(Calendar.MONTH) + 1)
                            },
                            modifier = Modifier.size(SimTongCalendarDateButtonSize)
                        ) {
                            Icon(
                                painter = painterResource(id = SimTongIcon.Calendar_After.drawableId),
                                contentDescription = SimTongIcon.Calendar_After.contentDescription
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(12.dp))

                WeekTopRow()
            }
        }
    }
}

@Composable
fun WeekTopRow() {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    ) {
        val list = listOf(Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday)

        items(list) {
            val textColor =
                if (it == Sunday || it == Saturday) SimTongColor.Gray300 else SimTongColor.Gray800

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillParentMaxWidth(1.toFloat() / 7.toFloat())
            ) {
                Body14(
                    text = it,
                    color = textColor
                )
            }
        }
    }
}

private object WeekOfDay {
    const val Sunday = "일"
    const val Monday = "월"
    const val Tuesday = "화"
    const val Wednesday = "수"
    const val Thursday = "목"
    const val Friday = "금"
    const val Saturday = "토"
}