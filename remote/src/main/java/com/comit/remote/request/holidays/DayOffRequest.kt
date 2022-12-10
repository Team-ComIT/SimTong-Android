package com.comit.remote.request.holidays

import com.google.gson.annotations.SerializedName

data class DayOffRequest(

    @field:SerializedName("date")
    val date: String
)
