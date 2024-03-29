package com.comit.data.datasource

import com.comit.model.Token
import com.comit.model.User
import java.util.UUID

interface RemoteAuthDataSource {

    suspend fun signIn(
        employeeNumber: Int,
        password: String,
        deviceToken: String,
    ): Token

    suspend fun verificationEmployee(
        name: String,
        employeeNumber: String,
    )

    suspend fun signUp(
        name: String,
        employeeNumber: Int,
        email: String,
        password: String,
        nickname: String?,
        profileImagePath: String?,
        deviceToken: String,
    ): Token

    suspend fun checkNicknameDuplication(
        nickname: String,
    )

    suspend fun changeSpot(
        spotId: UUID,
    )

    suspend fun changeProfileImage(
        profileImagePath: String,
    )

    suspend fun changeEmail(
        email: String,
    )

    suspend fun changeNickname(
        nickname: String,
    )

    suspend fun fetchUserInformation(): User
}
