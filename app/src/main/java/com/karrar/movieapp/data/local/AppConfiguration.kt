package com.karrar.movieapp.data.local


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface AppConfiguration {

    fun readString(key: String): String?

    suspend fun writeString(key: String, value: String)
}

class AppConfigurator @Inject constructor(private val dataStorePreferences: DataStorePreferences) :AppConfiguration{

    override fun readString(key: String):String? {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key,value)
    }
}