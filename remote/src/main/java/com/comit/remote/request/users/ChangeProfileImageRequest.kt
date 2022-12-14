package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class ChangeProfileImageRequest(
    @SerializedName("profile_image_path")
    val profileImagePath: String,
)
