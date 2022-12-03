package com.comit.remote.datasource

import com.comit.data.datasource.RemoteCommonsDataSource
import com.comit.model.SpotList
import com.comit.model.Token
import com.comit.remote.api.CommonsAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.commons.InitializationPasswordRequest
import javax.inject.Inject

class RemoteCommonsDataSourceImpl @Inject constructor(
    private val commonsAPI: CommonsAPI,
) : RemoteCommonsDataSource {

    override suspend fun findEmployeeNumber(
        name: String,
        spotId: String,
        email: String,
    ): String {
        return commonsAPI.findEmployeeNumber(
            name = name,
            spotId = spotId,
            email = email,
        ).employeeNumber
    }

    override suspend fun tokenReissue(
        refreshToken: String,
    ): Token {
        return try {
            commonsAPI.tokenReissue(
                refreshToken = refreshToken,
            ).toModel()
        } catch(e: Throwable) {
            throw IllegalStateException("Need Login")
        }
    }

    override suspend fun findAccountExist(
        employeeNumber: Int,
        email: String,
    ) {
        commonsAPI.findAccountExist(
            employeeNumber = employeeNumber,
            email = email,
        )
    }

    override suspend fun findEmailDuplication(
        email: String,
    ) {
        commonsAPI.findEmailDuplication(
            email = email,
        )
    }

    override suspend fun changePassword(
        password: String,
        newPassword: String,
    ) {
        commonsAPI.changePassword(
            password = password,
            newPassword = newPassword,
        )
    }

    override suspend fun fetchSpots(): SpotList {
        return commonsAPI.fetchSpots().toModel()
    }

    override suspend fun initializationPassword(
        email: String,
        employeeNumber: Int,
        newPassword: String,
    ) {
        commonsAPI.initializationPassword(
            request = InitializationPasswordRequest(
                email = email,
                employeeNumber = employeeNumber,
                newPassword = newPassword
            )
        )
    }
}
