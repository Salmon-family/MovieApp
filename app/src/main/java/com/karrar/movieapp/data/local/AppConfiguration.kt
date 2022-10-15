package com.karrar.movieapp.data.local


import com.karrar.movieapp.utilities.DataStorePreferencesKeys
import javax.inject.Inject

interface AppConfiguration {

    suspend fun getSessionId(): String

    suspend fun saveSessionId(value: String)
}

class AppConfigurationImp @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {
    override suspend fun getSessionId(): String {
        return dataStorePreferences.readString(DataStorePreferencesKeys.SESSION_ID_KEY) ?: ""
    }

    override suspend fun saveSessionId(value: String) {
        dataStorePreferences.writeString(DataStorePreferencesKeys.SESSION_ID_KEY, value)
    }
}