package com.thechance.repository.configuration

import com.thechance.local.DataStorePreferences
import javax.inject.Inject

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun getSessionId(): String? {
        return dataStorePreferences.readString(SESSION_ID_KEY)
    }

    override suspend fun saveSessionId(value: String) {
        dataStorePreferences.writeString(SESSION_ID_KEY, value)
    }

    override suspend fun saveRequestDate(key: String, value: Long) {
        dataStorePreferences.writeLong(key, value)
    }

    override suspend fun getRequestDate(key: String): Long? {
        return dataStorePreferences.readLong(key)
    }


    companion object DataStorePreferencesKeys {
        const val SESSION_ID_KEY = "session_id"
    }
}