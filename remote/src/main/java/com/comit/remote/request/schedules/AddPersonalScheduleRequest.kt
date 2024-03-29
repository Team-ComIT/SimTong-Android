package com.comit.remote.request.schedules

import com.google.gson.annotations.SerializedName

data class AddPersonalScheduleRequest(

    @SerializedName("title")
    val title: String,

    @SerializedName("start_at")
    val startAt: String,

    @SerializedName("end_at")
    val endAt: String,

    @SerializedName("alarm")
    val alarm: String ?,
)
