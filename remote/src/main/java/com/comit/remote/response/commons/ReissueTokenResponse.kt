package com.comit.remote.response.commons

import com.google.gson.annotations.SerializedName

data class ReissueTokenResponse(

    @field:SerializedName("access_token")
    val accessToken: String,

    @field:SerializedName("access_token_exp")
    val access_token_exp: String,

    @field:SerializedName("refresh_token")
    val refresh_token: String,
)
