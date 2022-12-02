package com.comit.domain.repository

interface EmailRepository {

    suspend fun sendEmailCode(
        email: String,
    )

    suspend fun checkEmailCode(
        email: String,
        code: String,
    )
}
