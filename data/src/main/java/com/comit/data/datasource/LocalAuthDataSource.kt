package com.comit.data.datasource

import com.comit.model.Token
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface LocalAuthDataSource {

    fun fetchAccessToken(): Flow<String>
    fun fetchRefreshToken(): Flow<String>
    fun fetchExpiredAt(): Flow<LocalDateTime>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveExpiredAt(expiredAt: LocalDateTime)

    suspend fun saveToken(token: Token)
}
