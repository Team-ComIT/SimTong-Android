package com.comit.remote.api

import com.comit.remote.request.holidays.DayOffRequest
import com.comit.remote.response.holidays.FetchHolidaysResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query
import java.util.Date

interface HolidayAPI {

    @GET("$HOLIDAYS/individual")
    suspend fun fetchHolidays(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
        @Query("status") status: String,
    ): FetchHolidaysResponse

    @POST("$HOLIDAYS/dayoff")
    suspend fun dayOffHolidays(
        @Body dayOffRequest: DayOffRequest
    )

    @PUT("$HOLIDAYS/annual")
    suspend fun setAnnual(
        @Query("date") date: Date,
    )

    @DELETE("$HOLIDAYS/work")
    suspend fun setWork(
        @Query("date") date: Date
    )

    private companion object {
        const val HOLIDAYS = "holidays"
    }
}
