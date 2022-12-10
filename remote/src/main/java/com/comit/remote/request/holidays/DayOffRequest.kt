package com.comit.remote.request.holidays

import com.google.gson.annotations.SerializedName
import java.util.Date

data class DayOffRequest(

    @field:SerializedName("date")
    val date: String
)
