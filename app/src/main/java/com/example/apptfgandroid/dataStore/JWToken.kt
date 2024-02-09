package com.example.apptfgandroid.dataStore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class JWToken(val value: String)

class TokenManager(private val dataStore: DataStore<Preferences>) {

    suspend fun saveJWToken(jwToken: JWToken) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey("jw_token")] = jwToken.value
        }
    }

    fun observeJWToken(): Flow<JWToken?> {
        return dataStore.data.map { preferences ->
            val jwTokenValue = preferences[stringPreferencesKey("jw_token")]
            JWToken(value = jwTokenValue.orEmpty())
        }
    }
}