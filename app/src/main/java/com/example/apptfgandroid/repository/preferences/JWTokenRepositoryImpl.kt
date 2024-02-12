package com.example.apptfgandroid.repository.preferences

import com.example.apptfgandroid.data.Preferences.JWToken.PreferenceToken

class JWTokenRepositoryImpl(
    private val preferenceToken: PreferenceToken
): JWTokenRepository{
    override suspend fun saveToken(key: String, value: String) {
        preferenceToken.saveToken(key, value)
    }

    override suspend fun getToken(key: String): String? {
        return preferenceToken.getToken(key)
    }
}