package com.karrar.movieapp.data.local


import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface DataStore {

    fun readString(key: String): Flow<String?>

    suspend fun writeString(key: String, value: String)
}

class DataStoreImp @Inject constructor(private val dataStorePreferences: DataStorePreferences) :DataStore{
    override fun readString(key: String): Flow<String?> {
        return dataStorePreferences.readString(key)
    }

    override suspend fun writeString(key: String, value: String) {
        dataStorePreferences.writeString(key,value)
    }
}