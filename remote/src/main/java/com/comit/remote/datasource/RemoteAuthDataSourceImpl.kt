package com.comit.remote.datasource

import com.comit.data.datasource.RemoteAuthDataSource
import com.comit.model.Token
import com.comit.remote.api.AuthAPI
import com.comit.remote.mapper.toModel
import com.comit.remote.request.users.SignInRequest
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : RemoteAuthDataSource {

    override suspend fun signIn(
        employeeNumber: Int,
        password: String
    ): Token {
        return authAPI.signIn(
            request = SignInRequest(
                employeeNumber = employeeNumber,
                password = password,
            )
        ).toModel()
    }
}
