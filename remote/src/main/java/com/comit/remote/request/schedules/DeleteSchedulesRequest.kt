package com.comit.remote.request.schedules

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class DeleteSchedulesRequest(

    @field:SerializedName("schedule-id")
    val scheduleId: UUID,
)
