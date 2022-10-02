package com.comit.remote.response.schedules

import com.google.gson.annotations.SerializedName

data class SimpleSchedulesResponse(

    @field:SerializedName("schedules")
    val schedules: List<Schedules>,
) {
    data class Schedules(

        @field:SerializedName("start_at")
        val start_at: String,

        @field:SerializedName("end_at")
        val end_at: String,
    )
}
