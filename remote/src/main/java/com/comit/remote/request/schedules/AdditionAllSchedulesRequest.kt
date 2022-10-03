package com.comit.remote.request.schedules

import com.google.gson.annotations.SerializedName
import java.util.Date

data class AdditionAllSchedulesRequest(

    @field:SerializedName("color")
    val color: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("content")
    val content: String,

    @field:SerializedName("start_at")
    val startAt: Date,

    @field:SerializedName("end_at")
    val endAt: Date,
)
