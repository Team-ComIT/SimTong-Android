package com.comit.remote.datasource

import com.comit.data.datasource.RemoteEmailDataSource
import com.comit.remote.api.EmailAPI
import javax.inject.Inject

class RemoteEmailDataSourceImpl @Inject constructor(
    private val emailAPI: EmailAPI,
) : RemoteEmailDataSource {

    override suspend fun sendEmailCode(
        email: String,
    ) {
        return emailAPI.sendEmailCode(
            email = email,
        )
    }

    override suspend fun checkEmailCode(
        email: String,
        code: String,
    ) {
        return emailAPI.checkEmailCode(
            email = email,
            code = code,
        )
    }
}
