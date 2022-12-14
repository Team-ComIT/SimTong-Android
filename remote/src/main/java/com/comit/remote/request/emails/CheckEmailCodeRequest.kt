package com.comit.remote.request.emails

import com.google.gson.annotations.SerializedName

data class CheckEmailCodeRequest(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("code")
    val code: String,
)
