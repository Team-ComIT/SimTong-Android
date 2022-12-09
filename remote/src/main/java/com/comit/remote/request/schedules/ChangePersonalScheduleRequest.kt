package com.comit.remote.request.schedules

import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.util.Date

data class ChangePersonalScheduleRequest(

    @SerializedName("title")
    val title: String,

    @SerializedName("start_at")
    val startAt: Date,

    @SerializedName("end_at")
    val endAt: Date,

    @SerializedName("alarm")
    val alarm: Time?,
)
