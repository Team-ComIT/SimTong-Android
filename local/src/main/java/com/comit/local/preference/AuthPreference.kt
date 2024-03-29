package com.comit.local.preference

import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

interface AuthPreference {

    fun fetchAccessToken(): Flow<String>
    fun fetchRefreshToken(): Flow<String>
    fun fetchExpiredAt(): Flow<LocalDateTime>

    suspend fun saveAccessToken(token: String)
    suspend fun saveRefreshToken(token: String)
    suspend fun saveExpiredAt(expiredAt: LocalDateTime)

    suspend fun clearAccessToken()
    suspend fun clearRefreshToken()
    suspend fun clearExpiredAt()

    suspend fun fetchDeviceToken(): Flow<String>
    suspend fun saveDeviceToken(token: String)
}
