package com.example.apptfgandroid.data.Preferences.JWToken

interface PreferenceToken {
    suspend fun saveToken(key: String, value: String)
    suspend fun getToken(key: String): String?
}