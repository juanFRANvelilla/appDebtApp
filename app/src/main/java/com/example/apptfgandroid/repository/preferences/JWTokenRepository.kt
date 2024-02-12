package com.example.apptfgandroid.repository.preferences

interface JWTokenRepository {
    suspend fun saveToken(key: String, value: String)
    suspend fun getToken(key: String): String?
}