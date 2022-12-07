package com.comit.domain.repository

import com.comit.domain.usecase.commons.CheckOldPasswordUseCase
import com.comit.model.SpotList

interface CommonsRepository {

    suspend fun findEmployeeNumber(
        name: String,
        spotId: String,
        email: String,
    ): String

    suspend fun tokenReissue(
        refreshToken: String,
    )

    suspend fun findAccountExist(
        employeeNumber: Int,
        email: String
    )

    suspend fun findEmailDuplication(
        email: String
    )

    suspend fun changePassword(
        password: String,
        newPassword: String,
    )

    suspend fun checkOldPassword(
        oldPassword: String,
    )

    suspend fun fetchSpots(): SpotList

    suspend fun initializationPassword(
        email: String,
        employeeNumber: Int,
        newPassword: String,
    )
}
