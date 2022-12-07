package com.comit.data.interceptor

import com.google.gson.annotations.SerializedName

data class ReissueTokenResponse(

    @field:SerializedName("access_token")
    val accessToken: String,

    @field:SerializedName("access_token_exp")
    val accessTokenExp: String,

    @field:SerializedName("refresh_token")
    val refreshToken: String,
)
