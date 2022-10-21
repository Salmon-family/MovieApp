package com.karrar.movieapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStorePreferences (context: Context) {
    private val Context.preferencesDataStore: DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(PREFERENCES_FILE_NAME)
    private val prefDataStore = context.preferencesDataStore

    suspend fun writeLong(key: String, value: Long) {
        prefDataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    suspend fun readLong(key: String): Long? {
        return prefDataStore.data.firstOrNull()?.get(longPreferencesKey(key))
    }

    suspend fun writeString(key: String, value: String){
        prefDataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun readString(key: String): Flow<String?> {
        return prefDataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    companion object {
        private const val PREFERENCES_FILE_NAME = "movie"
    }
}