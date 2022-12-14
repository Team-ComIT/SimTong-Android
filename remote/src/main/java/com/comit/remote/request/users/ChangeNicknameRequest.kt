package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class ChangeNicknameRequest(

    @field:SerializedName("nickname")
    val nickname: String,
)
