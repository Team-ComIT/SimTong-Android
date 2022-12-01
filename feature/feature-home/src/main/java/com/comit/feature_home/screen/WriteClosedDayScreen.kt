package com.comit.feature_home.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.comit.common.compose.noRippleClickable
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.typography.Body1
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body6
import com.example.feature_home.R
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun WriteClosedDayScreen() {
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    var year by remember { mutableStateOf("0000") }
    var month by remember { mutableStateOf("00") }
    var day by remember { mutableStateOf("0") }
    var workState = ""
    var workStateText by remember { mutableStateOf("") }

    SimBottomSheetDialog(
        useHandle = true,
        sheetState = bottomSheetState,
        sheetContent = {
            val saveEnabled = workState != workStateText
            val saveColor =
                if (saveEnabled) SimTongColor.MainColor400 else SimTongColor.MainColor100

            Column() {
                Row(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 30.dp, top = 17.dp)
                        .wrapContentHeight(Alignment.CenterVertically)
                ) {
                    val yearT = year + stringResource(id = R.string.calendar_year) + " "
                    val monthT = month + stringResource(id = R.string.calendar_month) + " "
                    val dayT = day + stringResource(id = R.string.calendar_day) + "은 "

                    Body6(
                        text = "$yearT$monthT$dayT\"$workStateText\"입니다.",
                        color = SimTongColor.Gray800
                    )

                    Body5(
                        text = stringResource(id = R.string.save),
                        color = saveColor,
                        onClick = {
                            if (saveEnabled) {
                                coroutineScope.launch {
                                    bottomSheetState.hide()
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .width(240.dp)
                ) {
                    WriteCloseDayItem(
                        text = stringResource(id = R.string.work_close),
                        color = SimTongColor.MainColor400,
                        onItemClicked = {
                            workStateText = it
                        }
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    WriteCloseDayItem(
                        text = stringResource(id = R.string.work_annual),
                        color = SimTongColor.FocusBlue,
                        onItemClicked = {
                            workStateText = it
                        }
                    )

                    Spacer(modifier = Modifier.width(30.dp))

                    WriteCloseDayItem(
                        text = stringResource(id = R.string.work_work),
                        color = SimTongColor.Gray200,
                        onItemClicked = {
                            workStateText = it
                        }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(22.5.dp))

            Row(
                modifier = Modifier
                    .padding(horizontal = 26.dp)
            ) {
                IconButton(
                    modifier = Modifier.size(24.dp),
                    onClick = {
                        // TODO: 화면 뒤로 이동
                    }
                ) {
                    Icon(
                        painter = painterResource(id = SimTongIcon.Back_Big.drawableId),
                        contentDescription = SimTongIcon.Back_Big.contentDescription
                    )
                }
                Body3(
                    text = stringResource(id = R.string.schedule_day_write),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(end = 24.dp)
                )
            }

            Spacer(modifier = Modifier.height(44.dp))

            SimTongCalendar(
                coroutineScope = coroutineScope,
                bottomSheetValue = bottomSheetState,
                onDateClicked = { _year, _month, _day, _workState ->
                    year = _year
                    month = _month
                    day = _day
                    workState = _workState
                    workStateText = _workState
                }
            )
        }
    }
}

@Composable
fun WriteCloseDayItem(
    text: String,
    color: Color,
    onItemClicked: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .noRippleClickable { onItemClicked(text) }
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(60.dp)
                .background(
                    color = color,
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Body1(
                text = text[0].toString(),
                color = SimTongColor.White,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Body6(text = text)
    }
}

@ExperimentalMaterialApi
@Composable
@Preview(showBackground = true)
fun ShowWriteClosedDayScreen() {
    WriteClosedDayScreen()
}
