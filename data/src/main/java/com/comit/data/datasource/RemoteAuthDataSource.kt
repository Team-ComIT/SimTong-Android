package com.comit.data.datasource

import com.comit.model.Token

interface RemoteAuthDataSource {

    suspend fun signIn(
        employeeNumber: Int,
        password: String,
    ): Token
}
