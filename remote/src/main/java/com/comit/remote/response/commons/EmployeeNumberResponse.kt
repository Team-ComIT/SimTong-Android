package com.comit.remote.response.commons

import com.google.gson.annotations.SerializedName

data class EmployeeNumberResponse(

    @field:SerializedName("employee_number")
    val employee_number: String,
)
