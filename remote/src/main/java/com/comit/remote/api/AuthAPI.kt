package com.comit.remote.api

import com.comit.remote.request.users.SignInRequest
import com.comit.remote.response.users.SignInResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthAPI {

    @POST("users/tokens")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponse
}
