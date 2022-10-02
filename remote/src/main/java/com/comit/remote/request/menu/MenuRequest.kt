package com.comit.remote.request.menu

import com.google.gson.annotations.SerializedName
import java.util.*

data class MenuRequest(

    @field:SerializedName("date")
    val date: Date,
)
