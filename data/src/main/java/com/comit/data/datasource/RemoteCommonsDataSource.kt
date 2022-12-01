package com.comit.data.datasource

import com.comit.model.SpotList
import com.comit.model.Token


interface RemoteCommonsDataSource {

    suspend fun findEmployeeNumber(
        name: String,
        spotId: String,
        email: String,
    ): String

    suspend fun tokenReissue(): Token

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

    suspend fun fetchSpots(): SpotList

    suspend fun initializationPassword(
        email: String,
        employeeNumber: Int,
        newPassword: String,
    )
}