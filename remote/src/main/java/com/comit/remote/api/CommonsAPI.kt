package com.comit.remote.api

import com.comit.remote.request.commons.ChangePasswordRequest
import com.comit.remote.request.commons.InitializationPasswordRequest
import com.comit.remote.response.commons.EmployeeNumberResponse
import com.comit.remote.response.commons.FetchSpotsResponse
import com.comit.remote.response.commons.ReissueTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface CommonsAPI {

    @GET("$COMMONS/employee-number")
    suspend fun findEmployeeNumber(
        @Query("name") name: String,
        @Query("spotId") spotId: String,
        @Query("email") email: String,
    ): EmployeeNumberResponse

    @PUT("$COMMONS/token/reissue")
    suspend fun tokenReissue(
        @Query("Refresh-Token") refreshToken: String,
    ): ReissueTokenResponse

    @GET("$COMMONS/account/existence")
    suspend fun findAccountExist(
        @Query("employeeNumber") employeeNumber: Int,
        @Query("email") email: String
    )

    @GET("$COMMONS/email/duplication")
    suspend fun findEmailDuplication(
        @Query("email") email: String
    )

    @PUT("$COMMONS/password")
    suspend fun changePassword(
        @Body request: ChangePasswordRequest,
    )

    @GET("$COMMONS/password/compare")
    suspend fun checkOldPassword(
        @Query("password") oldPassword: String,
    )

    @GET("$COMMONS/spot")
    suspend fun fetchSpots(): FetchSpotsResponse

    @PUT("$COMMONS/password/initialization")
    suspend fun initializationPassword(
        @Body request: InitializationPasswordRequest,
    )

    private companion object {
        const val COMMONS = "commons"
    }
}
