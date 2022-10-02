package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class ChangePasswordRequest(

    @field:SerializedName("email")
    val email: String,

    @field:SerializedName("employee_number")
    val employeeNumber: String,

    @field:SerializedName("new_password")
    val newPassword: String,
)
