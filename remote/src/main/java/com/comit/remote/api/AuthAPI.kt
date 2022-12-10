package com.comit.remote.api

import com.comit.remote.request.users.ChangeEmailRequest
import com.comit.remote.request.users.ChangeNicknameRequest
import com.comit.remote.request.users.ChangeProfileImageRequest
import com.comit.remote.request.users.ChangeSpotRequest
import com.comit.remote.request.users.SignInRequest
import com.comit.remote.request.users.SignUpRequest
import com.comit.remote.response.users.FetchUserInformationResponse
import com.comit.remote.response.users.SignInResponse
import com.comit.remote.response.users.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface AuthAPI {

    @POST("$USERS/tokens")
    suspend fun signIn(
        @Body request: SignInRequest
    ): SignInResponse

    @GET("$USERS/verification-employee")
    suspend fun verificationEmployee(
        @Query("name") name: String,
        @Query("employee_number") employeeNumber: String,
    )

    @POST("$USERS")
    suspend fun signUp(
        @Body request: SignUpRequest,
    ): SignUpResponse

    @GET("$USERS/nickname/duplication")
    suspend fun checkNicknameDuplication(
        @Query("nickname") nickname: String,
    )

    @PUT("$USERS/spot")
    suspend fun changeSpot(
        @Body request: ChangeSpotRequest
    ): Response<Unit>

    @PUT("$USERS/profile-image")
    suspend fun changeProfileImage(
        @Body request: ChangeProfileImageRequest,
    )

    @PUT("$USERS/email")
    suspend fun changeEmail(
        @Body request: ChangeEmailRequest,
    )

    @PUT("$USERS/nickname")
    suspend fun changeNickname(
        @Body request: ChangeNicknameRequest,
    ): Response<Unit>

    @GET("$USERS/information")
    suspend fun fetchUserInformation(): FetchUserInformationResponse

    private companion object {
        const val USERS = "users"
    }
}
