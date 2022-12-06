package com.comit.local.datasource

import com.comit.data.datasource.LocalAuthDataSource
import com.comit.local.preference.AuthPreference
import com.comit.model.Token
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.DateTimeParseException
import java.time.temporal.ChronoField
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : LocalAuthDataSource {

    override fun fetchAccessToken(): Flow<String> {
        return authPreference.fetchAccessToken()
    }

    override fun fetchRefreshToken(): Flow<String> {
        return authPreference.fetchRefreshToken()
    }

    override fun fetchExpiredAt(): Flow<LocalDateTime> {
        return authPreference.fetchExpiredAt()
    }

    override suspend fun saveAccessToken(token: String) {
        authPreference.saveAccessToken(
            token = token,
        )
    }

    override suspend fun saveRefreshToken(token: String) {
        authPreference.saveRefreshToken(
            token = token,
        )
    }

    override suspend fun saveExpiredAt(expiredAt: LocalDateTime) {
        authPreference.saveExpiredAt(
            expiredAt = expiredAt,
        )
    }

    override suspend fun saveToken(token: Token) {
        saveAccessToken(token.accessToken)
        saveRefreshToken(token.refreshToken)
        saveExpiredAt(token.accessTokenExp)
    }
}