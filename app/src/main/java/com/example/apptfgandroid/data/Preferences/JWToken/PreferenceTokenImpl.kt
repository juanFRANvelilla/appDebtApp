package com.example.apptfgandroid.data.Preferences.JWToken

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first

private const val MANAGETOKEN = "manage_token"

private val Context.datStore by preferencesDataStore(name = MANAGETOKEN)

class PreferenceTokenImpl (
    private val context: Context
): PreferenceToken {
    override suspend fun saveToken(key: String, value: String) {
        val preferenceKeyToken = stringPreferencesKey(key)
        context.datStore.edit { token ->
            token[preferenceKeyToken] = value

        }
    }

    override suspend fun getToken(key: String): String? {
        return try {
            val preferenceKeyToken = stringPreferencesKey(key)
            val token = context.datStore.data.first()
            token[preferenceKeyToken]
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}