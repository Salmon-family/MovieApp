package com.karrar.movieapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class DataStorePreferences(context: Context) {
    private val Context.preferencesDataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore

    suspend fun writeBoolean(key: String, value: Boolean) {
        prefDataStore.edit { preferences ->
            preferences[booleanPreferencesKey(key)] = value
        }
    }

    fun readBoolean(key: String): Flow<Boolean?> {
        return prefDataStore.data.map { preferences ->
            preferences[booleanPreferencesKey(key)]
        }
    }

    suspend fun writeString(key: String, value: String) {
        prefDataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun readString(key: String): String? {
//        return prefDataStore.data.map { preferences ->
//            preferences[stringPreferencesKey(key)]
//        }
        return runBlocking { prefDataStore.data.map { it[stringPreferencesKey(key)] }.first() }
    }

    companion object {
        private const val PREFERENCES_FILE_NAME = "movie"
    }
}