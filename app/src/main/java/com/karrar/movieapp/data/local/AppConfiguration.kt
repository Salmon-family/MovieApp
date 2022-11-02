package com.karrar.movieapp.data.local


import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): String?

    suspend fun writeString(key: String, value: String)

    suspend fun saveActorsRequestDate(value: Long)

    suspend fun getActorsRequestDate(): Long?


}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun readString(key: String): String? {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key, value)
    }

    override suspend fun saveActorsRequestDate(value: Long) {
        dataStorePreferences.writeLong(ACTOR_REQUEST_DATE_KEY, value)
    }

    override suspend fun getActorsRequestDate(): Long? {
        return dataStorePreferences.readLong(ACTOR_REQUEST_DATE_KEY)
    }

    companion object DataStorePreferencesKeys {
        const val SESSION_ID_KEY = "session_id"
        const val ACTOR_REQUEST_DATE_KEY = "request_date"
    }
}