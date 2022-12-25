package com.comit.remote.api

import com.comit.remote.response.menu.MenuResponse
import com.comit.remote.response.menu.PublicMenuResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface MenuAPI {

    @GET("$MENU")
    suspend fun fetchMenu(
        @Query("start_at") startAt: String,
        @Query("end_at") endAt: String,
    ): MenuResponse

    @GET("$MENU/public")
    suspend fun fetchPublicMenu(
        @Query("date") date: Date,
    ): PublicMenuResponse

    private companion object {
        const val MENU = "menu"
    }
}
