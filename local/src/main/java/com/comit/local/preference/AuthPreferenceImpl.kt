package com.comit.local.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.comit.local.extension.epochSecondToLocalDateTime
import com.comit.local.extension.fetchLongPreference
import com.comit.local.extension.fetchStringPreference
import com.comit.local.extension.toEpochSecond
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val context: Context,
) : AuthPreference {

    override fun fetchAccessToken(): Flow<String> {
        return context.fetchStringPreference(
            key = ACCESS_TOKEN,
        )
    }

    override fun fetchRefreshToken(): Flow<String> {
        return context.fetchStringPreference(
            key = REFRESH_TOKEN,
        )
    }

    override fun fetchExpiredAt(): Flow<LocalDateTime> {
        return context.fetchLongPreference(
            key = EXPIRED_AT,
        ).map {
            it.epochSecondToLocalDateTime()
        }
    }

    override suspend fun saveAccessToken(
        token: String,
    ) {
        context.datastore.edit { preference ->
            preference[ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(
        token: String,
    ) {
        context.datastore.edit { preference ->
            preference[REFRESH_TOKEN] = token
        }
    }

    override suspend fun saveExpiredAt(
        expiredAt: LocalDateTime,
    ) {
        context.datastore.edit { preference ->
            preference[EXPIRED_AT] = expiredAt.toEpochSecond()
        }
    }

    override suspend fun fetchDeviceToken(): Flow<String> {
        return context.fetchStringPreference(
            key = DEVICE_TOKEN,
        )
    }

    override suspend fun saveDeviceToken(token: String) {
        context.datastore.edit { preference ->
            preference[DEVICE_TOKEN] = token
        }
    }

    override suspend fun clearAccessToken() {
        context.datastore.edit { preference ->
            preference.remove(ACCESS_TOKEN)
        }
    }

    override suspend fun clearRefreshToken() {
        context.datastore.edit { preference ->
            preference.remove(REFRESH_TOKEN)
        }
    }

    override suspend fun clearExpiredAt() {
        context.datastore.edit { preference ->
            preference.remove(EXPIRED_AT)
        }
    }

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey("ACCESS_TOKEN")
        val REFRESH_TOKEN = stringPreferencesKey("REFRESH_TOKEN")
        val EXPIRED_AT = longPreferencesKey("EXPIRED_AT")
        val DEVICE_TOKEN = stringPreferencesKey("DEVICE_TOKEN")
    }
}
