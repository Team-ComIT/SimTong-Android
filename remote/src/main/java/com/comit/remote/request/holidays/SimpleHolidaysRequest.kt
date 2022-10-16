package com.comit.remote.request.holidays

import com.google.gson.annotations.SerializedName

data class SimpleHolidaysRequest(

    @field:SerializedName("years")
    val years: Int,

    @field:SerializedName("months")
    val months: Int,
)
