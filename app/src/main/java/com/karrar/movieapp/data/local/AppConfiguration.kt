package com.karrar.movieapp.data.local


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): Flow<String?>

    suspend fun writeString(key: String, value: String)

    suspend fun saveRequestDate(value: Long)

    suspend fun getRequestDate(): Long?
}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {
    override fun readString(key: String): Flow<String?> {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key, value)
    }

    override suspend fun saveRequestDate(value: Long) {
        dataStorePreferences.writeLong(REQUEST_DATE_KEY, value)
    }

    override suspend fun getRequestDate(): Long? {
        return dataStorePreferences.readLong(REQUEST_DATE_KEY)
    }

    companion object DataStorePreferencesKeys {
        const val SESSION_ID_KEY = "session_id"
        const val REQUEST_DATE_KEY = "request_date"
    }
}