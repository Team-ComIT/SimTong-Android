package com.comit.remote.api

import com.comit.remote.request.holidays.DayOffRequest
import com.comit.remote.request.holidays.SetAnnualRequest
import com.comit.remote.request.holidays.SetWorkRequest
import com.comit.remote.response.holidays.CheckLeftHolidayResponse
import com.comit.remote.response.holidays.FetchHolidaysResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

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

    @POST("$HOLIDAYS/annual")
    suspend fun setAnnual(
        @Body setAnnualRequest: SetAnnualRequest,
    )

    @PUT("$HOLIDAYS/work")
    suspend fun setWork(
        @Body setWorkRequest: SetWorkRequest
    )

    @GET("$HOLIDAYS/annual/count")
    suspend fun checkLeftHoliday(
        @Query("year") year: Int
    ): CheckLeftHolidayResponse

    @GET("$HOLIDAYS/verification-period")
    suspend fun checkCanWriteHoliday()

    private companion object {
        const val HOLIDAYS = "holidays"
    }
}
