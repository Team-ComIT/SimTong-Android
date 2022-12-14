package com.comit.local.extension

import android.content.Context
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import com.comit.local.preference.datastore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

internal suspend fun returnEmptyForException(
    exception: Throwable
) {
    flow {
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }
}

internal fun Context.fetchStringPreference(
    key: Preferences.Key<String>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: ""
    }

internal fun Context.fetchBooleanPreference(
    key: Preferences.Key<Boolean>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: true
    }

internal fun Context.fetchIntPreference(
    key: Preferences.Key<Int>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: 0
    }

internal fun Context.fetchFloatPreference(
    key: Preferences.Key<Float>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: 0.0
    }

internal fun Context.fetchLongPreference(
    key: Preferences.Key<Long>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: 0L
    }

internal fun Context.fetchDoublePreference(
    key: Preferences.Key<Double>
) = this.datastore.data
    .catch { exception ->
        returnEmptyForException(
            exception = exception,
        )
    }
    .map { preference ->
        preference[key] ?: 0.0
    }
