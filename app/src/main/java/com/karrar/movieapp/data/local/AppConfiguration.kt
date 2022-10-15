package com.karrar.movieapp.data.local


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AppConfiguration {

    fun getSessionId(key: String): Flow<String?>

    suspend fun saveSessionId(key: String, value: String)
}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :AppConfiguration{
    override fun getSessionId(key: String): Flow<String?> {
        return dataStorePreferences.readString(key)
    }

    override suspend fun saveSessionId(key: String, value: String) {
        dataStorePreferences.writeString(key,value)
    }
}