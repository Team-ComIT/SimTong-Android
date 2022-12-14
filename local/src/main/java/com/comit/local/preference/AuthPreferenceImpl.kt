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

    companion object {
        val ACCESS_TOKEN = stringPreferencesKey(
            name = "ACCESS_TOKEN",
        )

        val REFRESH_TOKEN = stringPreferencesKey(
            name = "REFRESH_TOKEN",
        )

        val EXPIRED_AT = longPreferencesKey(
            name = "EXPIRED_AT"
        )
    }
}
