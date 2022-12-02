package com.comit.remote.api

import com.comit.remote.response.holidays.FetchHolidaysResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query
import java.util.Date

interface HolidayAPI {

    @GET("$HOLIDAYS")
    suspend fun fetchHolidays(
        @Query("date") date: Date,
    ): FetchHolidaysResponse

    @PUT("$HOLIDAYS/dayoff")
    suspend fun dayOffHolidays(
        @Query("date") date: Date
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
