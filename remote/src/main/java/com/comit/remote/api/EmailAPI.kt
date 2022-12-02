package com.comit.remote.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface EmailAPI {

    @POST("$EMAILS/code")
    suspend fun sendEmailCode(
        @Body email: String,
    )

    @GET("$EMAILS")
    suspend fun checkEmailCode(
        @Query("email") email: String,
        @Query("code") code: String,
    )

    private companion object {
        const val EMAILS = "emails"
    }
}
