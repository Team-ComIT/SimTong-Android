package com.comit.domain.repository

import com.comit.model.User
import java.io.File
import java.util.UUID

interface AuthRepository {

    suspend fun signIn(
        employeeNumber: Int,
        password: String,
    )

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
        profileImage: File?,
    )

    suspend fun checkNicknameDuplication(
        nickname: String,
    )

    suspend fun changeSpot(
        spotId: UUID,
    )

    suspend fun changeProfileImage(
        profileImg: File,
    )

    suspend fun changeEmail(
        email: String,
    )

    suspend fun changeNickname(
        nickname: String,
    )

    suspend fun fetchUserInformation(): User

    suspend fun saveDeviceToken(token: String)
}
