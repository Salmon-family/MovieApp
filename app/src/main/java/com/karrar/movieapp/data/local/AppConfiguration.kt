package com.karrar.movieapp.data.local


import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): String?

    suspend fun writeString(key: String, value: String)

    suspend fun saveRequestDate(key: String,value: Long)

    suspend fun getRequestDate(key: String): Long?

}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :
    AppConfiguration {

    override fun readString(key: String): String? {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key, value)
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