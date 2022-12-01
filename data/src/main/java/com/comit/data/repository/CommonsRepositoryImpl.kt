package com.comit.data.repository

import com.comit.data.datasource.RemoteCommonsDataSource
import com.comit.domain.repository.CommonsRepository
import com.comit.model.SpotList
import javax.inject.Inject

class CommonsRepositoryImpl @Inject constructor(
    private val remoteCommonsDataSource: RemoteCommonsDataSource,
): CommonsRepository {

    override suspend fun findEmployeeNumber(
        name: String,
        spotId: String,
        email: String,
    ): String {
        return remoteCommonsDataSource.findEmployeeNumber(
            name = name,
            spotId = spotId,
            email = email,
        )
    }

    override suspend fun tokenReissue()  {
        //TODO("local에 token 저장")
        remoteCommonsDataSource.tokenReissue()
    }

    override suspend fun findAccountExist(
        employeeNumber: Int,
        email: String,
    ) {
        remoteCommonsDataSource.findAccountExist(
            employeeNumber = employeeNumber,
            email = email,
        )
    }

    override suspend fun findEmailDuplication(
        email: String,
    ) {
        remoteCommonsDataSource.findEmailDuplication(
            email = email,
        )
    }

    override suspend fun changePassword(
        password: String,
        newPassword: String,
    ) {
        remoteCommonsDataSource.changePassword(
            password = password,
            newPassword = newPassword,
        )
    }

    override suspend fun fetchSpots(): SpotList {
        return remoteCommonsDataSource.fetchSpots()
    }

    override suspend fun initializationPassword(
        email: String,
        employeeNumber: Int,
        newPassword: String
    ) {
        remoteCommonsDataSource.initializationPassword(
            email = email,
            employeeNumber = employeeNumber,
            newPassword = newPassword,
        )
    }
}