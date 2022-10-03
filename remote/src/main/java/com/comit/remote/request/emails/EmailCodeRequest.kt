package com.comit.remote.request.emails

import com.google.gson.annotations.SerializedName

data class EmailCodeRequest(

    @field:SerializedName("email")
    val email: String,
)
