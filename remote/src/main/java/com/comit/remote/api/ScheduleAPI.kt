package com.comit.remote.api

import com.comit.remote.request.schedules.AddPersonalScheduleRequest
import com.comit.remote.request.schedules.ChangePersonalScheduleRequest
import com.comit.remote.response.schedules.FetchPersonalScheduleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date
import java.util.UUID

interface ScheduleAPI {

    @GET("$SCHEDULE")
    suspend fun fetchPersonalSchedule(
        @Query("date") date: Date,
    ): FetchPersonalScheduleResponse

    @POST("$SCHEDULE")
    suspend fun addPersonalSchedule(
        @Body request: AddPersonalScheduleRequest,
    ): Response<Unit>

    @PUT("$SCHEDULE/{schedule-id}")
    suspend fun changePersonalSchedule(
        @Path("schedule-id") scheduleId: UUID,
        @Body request: ChangePersonalScheduleRequest,
    )

    @DELETE("$SCHEDULE/{schedule-id}")
    suspend fun deletePersonalSchedule(
        @Path("schedule-id") scheduleId: UUID,
    ): Response<Unit>

    private companion object {
        const val SCHEDULE = "schedules"
    }
}
