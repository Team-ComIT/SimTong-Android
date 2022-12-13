@file:OptIn(InternalCoroutinesApi::class)
@file:Suppress("OPT_IN_IS_NOT_ENABLED", "MaxLineLength")

package com.comit.feature_home.screen.schedule

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.comit.common.rememberToast
import com.comit.core.observeWithLifecycle
import com.comit.core_design_system.button.SimTongButtonColor
import com.comit.core_design_system.button.SimTongSmallRoundButton
import com.comit.core_design_system.color.SimTongColor
import com.comit.core_design_system.component.BigHeader
import com.comit.core_design_system.dialog.SimBottomSheetDialog
import com.comit.core_design_system.icon.SimTongIcon
import com.comit.core_design_system.modifier.simClickable
import com.comit.core_design_system.typography.Body11
import com.comit.core_design_system.typography.Body3
import com.comit.core_design_system.typography.Body5
import com.comit.core_design_system.typography.Body7
import com.comit.feature_home.SubStringDay
import com.comit.feature_home.SubStringMonthEnd
import com.comit.feature_home.SubStringMonthStart
import com.comit.feature_home.SubStringYearEnd
import com.comit.feature_home.SubStringYearStart
import com.comit.feature_home.calendar.SimTongCalendar
import com.comit.feature_home.mvi.FetchScheduleSideEffect
import com.comit.feature_home.string
import com.comit.navigator.SimTongScreen
import com.example.feature_home.R
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import java.sql.Date
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.GregorianCalendar
import java.util.UUID

@Stable
private val HorizontalPadding = PaddingValues(
    horizontal = 30.dp
)

private val HomeCalendarHeight: Dp = 422.dp

@Stable
private val CalendarPadding = PaddingValues(
    horizontal = 20.dp
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowScheduleScreen(
    navController: NavController,
    showScheduleViewModel: ShowScheduleViewModel = hiltViewModel(),
) {
    val toast = rememberToast()

    val showScheduleContainer = showScheduleViewModel.container
    val showScheduleState = showScheduleContainer.stateFlow.collectAsState().value
    val showScheduleSideEffect = showScheduleContainer.sideEffectFlow

    val today = GregorianCalendar()
    val calendar = GregorianCalendar(
        today.get(Calendar.YEAR),
        today.get(Calendar.MONTH),
        today.get(Calendar.DATE)
    )

    var date by remember {
        mutableStateOf<Date>(
            Date.valueOf(
                string.format("%02d", calendar.get(Calendar.YEAR)) +
                    "-" +
                    string.format("%02d", calendar.get(Calendar.MONTH) + 1) +
                    "-01"
            )
        )
    }

    LaunchedEffect(showScheduleViewModel) {
        showScheduleViewModel.showSchedule(date)
    }

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    var scheduleId by remember { mutableStateOf("") }
    var scheduleTitle by remember { mutableStateOf("") }
    var scheduleStart by remember { mutableStateOf("") }
    var scheduleEnd by remember { mutableStateOf("") }

    showScheduleSideEffect.observeWithLifecycle() {
        when (it) {
            FetchScheduleSideEffect.FetchScheduleFail -> {
                toast(message = "일정을 불러오는데 실패했습니다.")
            }
            FetchScheduleSideEffect.DeleteScheduleSuccess -> {
                coroutineScope.launch {
                    bottomSheetState.hide()
                }
                showScheduleViewModel.showSchedule(date)
            }
            FetchScheduleSideEffect.DeleteScheduleFail -> {
                toast(message = "일정 삭제를 실패했습니다.")
            }
        }
    }

    Column {

        var deleteCheck by remember { mutableStateOf(false) }

        SimBottomSheetDialog(
            sheetState = bottomSheetState,
            sheetContent = {
                if (deleteCheck) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(HorizontalPadding)
                    ) {
                        Body3(text = "\"$scheduleTitle\"일정을 삭제합니다.")

                        Spacer(modifier = Modifier.height(20.dp))

                        Row() {
                            SimTongSmallRoundButton(
                                text = stringResource(id = R.string.cancel),
                                color = SimTongButtonColor.GRAY,
                                modifier = Modifier
                                    .width(150.dp)
                                    .height(50.dp)
                            ) {
                                deleteCheck = false
                            }

                            SimTongSmallRoundButton(
                                text = stringResource(id = R.string.confirm),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.End)
                                    .width(150.dp)
                                    .height(50.dp)
                            ) {
                                showScheduleViewModel.deleteSchedule(UUID.fromString(scheduleId))
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))
                    }
                } else {
                    Column(modifier = Modifier.height(200.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .simClickable {
                                    navController.navigate(
                                        route = SimTongScreen.Home.WRITE_SCHEDULE + "isNew${false}" + "scheduleId$scheduleId" + "title$scheduleTitle" + "scheduleStart$scheduleStart" + "scheduleEnd$scheduleEnd"
                                    )
                                }
                        ) {
                            Spacer(modifier = Modifier.width(30.dp))

                            Body5(text = stringResource(id = R.string.schedule_fix_do))
                        }

                        Canvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(HorizontalPadding)
                        ) {
                            val canvasWidth = size.width
                            val canvasHeight = size.height

                            drawLine(
                                start = Offset(x = 0f, y = canvasHeight),
                                end = Offset(x = canvasWidth, y = canvasHeight),
                                color = SimTongColor.Gray200,
                                strokeWidth = 2F,
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .simClickable {
                                    deleteCheck = true
                                }
                        ) {
                            Spacer(modifier = Modifier.width(30.dp))

                            Body5(text = stringResource(id = R.string.schedule_delete_do))
                        }
                    }
                }
            }
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                BigHeader(
                    text = stringResource(id = R.string.schedule_comment),
                    onPrevious = { navController.popBackStack() },
                    modifier = Modifier
                        .background(
                            color = SimTongColor.Background
                        ),
                )

                Spacer(modifier = Modifier.height(20.dp))

                SimTongCalendar(
                    onBeforeClicked = {
                        date = it
                        showScheduleViewModel.showSchedule(date)
                    },
                    onNextClicked = {
                        date = it
                        showScheduleViewModel.showSchedule(date)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HomeCalendarHeight)
                        .padding(CalendarPadding),
                )

                Spacer(modifier = Modifier.height(7.dp))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(HorizontalPadding)
                ) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height

                    drawLine(
                        start = Offset(x = 0f, y = canvasHeight),
                        end = Offset(x = canvasWidth, y = canvasHeight),
                        color = SimTongColor.Gray200,
                        strokeWidth = 2F,
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(HorizontalPadding)
                ) {
                    Body3(text = stringResource(id = R.string.schedule_all))

                    IconButton(
                        onClick = {
                            navController.navigate(
                                route = SimTongScreen.Home.WRITE_SCHEDULE +
                                    "isNew${true}" + "scheduleId${"k"}" +
                                    "title${"s"}" + "scheduleStart${"i"}" +
                                    "scheduleEnd${"a"}"
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.End)
                    ) {
                        Icon(
                            painter = painterResource(id = SimTongIcon.Add.drawableId),
                            contentDescription = SimTongIcon.Add.contentDescription,
                        )
                    }
                }

                LazyColumn() {
                    items(showScheduleState.scheduleList) {
                        ScheduleItem(
                            startAt = it.startAt,
                            endAt = it.endAt,
                            title = it.title,
                            onScheduleClicked = {
                                deleteCheck = false
                                coroutineScope.launch {
                                    bottomSheetState.show()
                                }
                                scheduleId = it.id
                                scheduleTitle = it.title
                                scheduleStart = it.startAt
                                scheduleEnd = it.endAt
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ScheduleItem(
    startAt: String,
    endAt: String,
    title: String,
    onScheduleClicked: () -> Unit,
) {
    var today = false

    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDateNow = dateTimeFormatter.format(LocalDate.now())
    val now = localDateNow.substring(SubStringYearStart, SubStringYearEnd) +
        localDateNow.substring(SubStringMonthStart, SubStringMonthEnd) +
        localDateNow.substring(SubStringDay)
    val start = startAt.substring(SubStringYearStart, SubStringYearStart) +
        startAt.substring(SubStringMonthStart, SubStringMonthEnd) +
        startAt.substring(SubStringDay)
    val end = endAt.substring(SubStringYearStart, SubStringYearEnd) +
        endAt.substring(SubStringMonthStart, SubStringMonthEnd) +
        endAt.substring(SubStringDay)

    if (start.toInt() <= now.toInt() && end >= now)
        today = true

    val titleColor = if (today) SimTongColor.Gray800 else SimTongColor.Gray300
    val dateColor = if (today) SimTongColor.MainColor400 else SimTongColor.Gray300

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(HorizontalPadding)
        ) {
            if (today) {
                Image(
                    painter = painterResource(id = SimTongIcon.Schedule_On.drawableId),
                    contentDescription = SimTongIcon.Schedule_On.contentDescription,
                )
            } else {
                Image(
                    painter = painterResource(id = SimTongIcon.Schedule_Off.drawableId),
                    contentDescription = SimTongIcon.Schedule_Off.contentDescription
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column {
                Body7(
                    text = title,
                    color = titleColor
                )

                Body11(
                    text = "$startAt ~ $endAt",
                    color = dateColor
                )
            }

            IconButton(
                onClick = {
                    onScheduleClicked()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
            ) {
                Icon(
                    painter = painterResource(id = SimTongIcon.Option_horizontal_Bold.drawableId),
                    contentDescription = SimTongIcon.Option_horizontal_Bold.contentDescription,
                    tint = SimTongColor.Gray300,
                )
            }
        }
    }
}
