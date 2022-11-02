package com.karrar.movieapp.data.local


import javax.inject.Inject

interface AppConfiguration {

    fun getSessionId(): String?

    suspend fun saveSessionId(value: String)

    suspend fun saveRequestDate(value: Long)

    suspend fun getRequestDate(): Long?
}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun getSessionId(): String? {
        return dataStorePreferences.readString(SESSION_ID_KEY)
    }

    override suspend fun saveSessionId(value: String) {
        dataStorePreferences.writeString(SESSION_ID_KEY, value)
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