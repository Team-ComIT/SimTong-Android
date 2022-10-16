package com.comit.remote.response.holidays

import com.google.gson.annotations.SerializedName

data class HolidaysResponse(

    @field:SerializedName("holidays")
    val holidays: List<Holidays>
) {

    data class Holidays(

        @field:SerializedName("date")
        val date: String,

        @field:SerializedName("title")
        val title: String,
    )
}
