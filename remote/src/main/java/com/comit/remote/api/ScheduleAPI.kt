package com.comit.remote.api

import com.comit.remote.request.schedules.AddPersonalScheduleRequest
import com.comit.remote.request.schedules.ChangePersonalScheduleRequest
import com.comit.remote.response.schedules.FetchPersonalScheduleResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface ScheduleAPI {

    @GET("$SCHEDULE")
    suspend fun fetchPersonalSchedule(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
    ): FetchPersonalScheduleResponse

    @POST("$SCHEDULE")
    suspend fun addPersonalSchedule(
        @Body request: AddPersonalScheduleRequest,
    )

    @PUT("$SCHEDULE/{schedule-id}")
    suspend fun changePersonalSchedule(
        @Path("schedule-id") scheduleId: UUID,
        @Body request: ChangePersonalScheduleRequest,
    )

    @DELETE("$SCHEDULE/{schedule-id}")
    suspend fun deletePersonalSchedule(
        @Path("schedule-id") scheduleId: UUID,
    )

    private companion object {
        const val SCHEDULE = "schedules"
    }
}
