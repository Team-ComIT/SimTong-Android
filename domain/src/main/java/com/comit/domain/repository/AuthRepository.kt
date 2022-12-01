package com.comit.domain.repository

import com.comit.model.Token

interface AuthRepository {

    suspend fun signIn(
        employeeNumber: Int,
        password: String,
    ): Token
}
