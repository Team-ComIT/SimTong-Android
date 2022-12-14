package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class SignUpRequest(
    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("employee_number")
    val employeeNumber: Int,

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("password")
    val password: String,

    @field:SerializedName("nickname")
    val nickname: String?,

    @field:SerializedName("profile_image_path")
    val profileImagePath: String?,
)
