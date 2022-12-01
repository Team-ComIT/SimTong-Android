package com.comit.remote.request.commons

import com.google.gson.annotations.SerializedName

data class InitializationPasswordRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("employee_number")
    val employeeNumber: Int,
    @SerializedName("new_password")
    val newPassword: String,
)
