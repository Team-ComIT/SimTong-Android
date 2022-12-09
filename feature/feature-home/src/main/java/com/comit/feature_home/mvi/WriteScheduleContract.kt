package com.comit.feature_home.mvi

data class WriteScheduleState(
    val title: String = "",
    val scheduleStart: String = "",
    val scheduleEnd: String = "",
    val alarm: String = "",

    val errMsgTitle: String ? = null,
    val errMsgScheduleStart: String ? = null,
    val errMsgScheduleEnd: String ? = null,
    val errMsgAlarm: String ? = null
)

sealed class WriteScheduleSideInEffect {

    object WriteScheduleSuccess : WriteScheduleSideInEffect()
}
