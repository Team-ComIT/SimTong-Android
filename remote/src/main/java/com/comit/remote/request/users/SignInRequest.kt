package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class SignInRequest(

    @field:SerializedName("employee_number")
    val employeeNumber: Int,

    @field:SerializedName("password")
    val password: String,
)
