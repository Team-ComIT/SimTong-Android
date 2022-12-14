package com.comit.remote.request.commons

import com.google.gson.annotations.SerializedName

data class ReissueTokenRequest(

    @field:SerializedName("Refresh-Token")
    val refreshToken: String,
)
