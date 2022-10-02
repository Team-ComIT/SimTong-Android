package com.comit.remote.response.users

import com.google.gson.annotations.SerializedName

data class SignInResponse(

    @field:SerializedName("access_token")
    val access_token: String,

    @field:SerializedName("access_token_exp")
    val accessTokenExp: String,

    @field:SerializedName("refresh_token")
    val refreshToken: String,
)
