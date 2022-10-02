package com.comit.remote.request.menu

import com.google.gson.annotations.SerializedName
import java.util.Date

data class MenuRequest(

    @field:SerializedName("date")
    val date: Date,
)
