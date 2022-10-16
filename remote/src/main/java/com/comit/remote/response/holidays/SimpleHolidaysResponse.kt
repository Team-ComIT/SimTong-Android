package com.comit.remote.response.holidays

import com.google.gson.annotations.SerializedName

data class SimpleHolidaysResponse(

    @field:SerializedName("holidays")
    val holidays: List<String>
)
