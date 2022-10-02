package com.comit.remote.request.commons

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class EmployeeNumberRequest(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("spotId")
    val spotId: UUID,

    @field:SerializedName("email")
    val email: String,
)
