package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class ChangeEmailRequest(

    @field:SerializedName("email")
    val email: String,
)
