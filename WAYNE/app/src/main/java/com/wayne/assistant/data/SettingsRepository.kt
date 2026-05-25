package com.wayne.assistant.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "wayne_settings")

class SettingsRepository(private val context: Context) {

    private object Keys {
        val API_KEY = stringPreferencesKey("api_key")
        val WAKE_WORD = stringPreferencesKey("wake_word")
    }

    val apiKey: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Keys.API_KEY] ?: ""
    }

    val wakeWord: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[Keys.WAKE_WORD] ?: "hey wayne"
    }

    suspend fun saveApiKey(value: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.API_KEY] = value
        }
    }

    suspend fun saveWakeWord(value: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.WAKE_WORD] = value
        }
    }
}
