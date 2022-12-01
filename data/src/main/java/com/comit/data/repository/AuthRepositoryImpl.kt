package com.comit.data.repository

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.domain.repository.AuthRepository
import com.comit.model.Token
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDataSource: RemoteAuthDataSource,
) : AuthRepository {

    override suspend fun signIn(
        employeeNumber: Int,
        password: String,
    ): Token {
        return remoteAuthDataSource.signIn(
            employeeNumber = employeeNumber,
            password = password,
        )
    }
}
