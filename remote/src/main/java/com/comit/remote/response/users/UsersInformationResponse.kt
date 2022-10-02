package com.comit.remote.response.users

import com.google.gson.annotations.SerializedName

data class UsersInformationResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("nickname")
    val nickname: String,

    @field:SerializedName("spot")
    val spot: String,

    @field:SerializedName("profile_image_path")
    val profile_image_path: String,
)
