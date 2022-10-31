package com.thechance.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class DataStorePreferences(context: Context) {
    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        PREFERENCES_FILE_NAME
    )
    private val prefDataStore = context.preferencesDataStore

    suspend fun writeLong(key: String, value: Long) {
        prefDataStore.edit { preferences ->
            preferences[longPreferencesKey(key)] = value
        }
    }

    suspend fun readLong(key: String): Long? {
        return prefDataStore.data.firstOrNull()?.get(longPreferencesKey(key))
    }

    suspend fun writeString(key: String, value: String) {
        prefDataStore.edit { preferences ->
            preferences[stringPreferencesKey(key)] = value
        }
    }

    fun readString(key: String): String? {
        return runBlocking { prefDataStore.data.map { it[stringPreferencesKey(key)] }.first() }
    }

    companion object {
        private const val PREFERENCES_FILE_NAME = "movie"
    }
}