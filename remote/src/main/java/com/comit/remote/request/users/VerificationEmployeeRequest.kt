package com.comit.remote.request.users

import com.google.gson.annotations.SerializedName

data class VerificationEmployeeRequest(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("employee_number")
    val employeeNumber: Int,
)
