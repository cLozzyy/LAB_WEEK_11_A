package com.example.lab_week_11_a.wrappers

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settingsStore")

class DataStoreWrapper (private val context: Context) {


    private val KEY_TEXT = stringPreferencesKey("key_text")


    val textFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            // Mengambil nilai, atau String kosong jika belum ada
            preferences[KEY_TEXT] ?: ""
        }


    suspend fun saveText(text: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_TEXT] = text
        }
    }
}