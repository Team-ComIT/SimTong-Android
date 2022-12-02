package com.comit.data.repository

import com.comit.data.datasource.RemoteEmailDataSource
import com.comit.domain.repository.EmailRepository
import javax.inject.Inject

class EmailRepositoryImpl @Inject constructor(
    private val remoteEmailDataSource: RemoteEmailDataSource,
) : EmailRepository {

    override suspend fun sendEmailCode(
        email: String,
    ) {
        remoteEmailDataSource.sendEmailCode(
            email = email,
        )
    }

    override suspend fun checkEmailCode(
        email: String,
        code: String,
    ) {
        remoteEmailDataSource.checkEmailCode(
            email = email,
            code = code,
        )
    }
}
