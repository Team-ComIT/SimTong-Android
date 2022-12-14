package com.comit.remote.response.holidays

import com.google.gson.annotations.SerializedName

data class FetchHolidaysResponse(

    @field:SerializedName("holidays")
    val holidays: List<Holiday>
) {

    data class Holiday(

        @field:SerializedName("date")
        val date: String,

        @field:SerializedName("type")
        val title: String,
    )
}
