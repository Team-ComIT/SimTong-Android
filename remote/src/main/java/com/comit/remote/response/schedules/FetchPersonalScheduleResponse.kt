package com.comit.remote.response.schedules

import com.google.gson.annotations.SerializedName

data class FetchPersonalScheduleResponse(

    @field:SerializedName("schedules")
    val schedules: List<Schedule>,
) {
    data class Schedule(

        @field:SerializedName("id")
        val id: String,

        @field:SerializedName("start_at")
        val startAt: String,

        @field:SerializedName("end_at")
        val endAt: String,

        @field:SerializedName("title")
        val title: String,
    )
}
