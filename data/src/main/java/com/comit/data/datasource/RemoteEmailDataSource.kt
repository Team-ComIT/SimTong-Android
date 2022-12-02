package com.comit.data.datasource

interface RemoteEmailDataSource {

    suspend fun sendEmailCode(
        email: String,
    )

    suspend fun checkEmailCode(
        email: String,
        code: String,
    )
}
