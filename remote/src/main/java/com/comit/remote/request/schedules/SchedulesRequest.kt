package com.comit.remote.request.schedules

import com.google.gson.annotations.SerializedName

data class SchedulesRequest(

    @field:SerializedName("years")
    val years: Int,

    @field:SerializedName("months")
    val months: Int,
)
