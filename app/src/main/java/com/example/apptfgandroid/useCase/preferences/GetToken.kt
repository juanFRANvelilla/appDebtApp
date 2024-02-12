package com.example.apptfgandroid.useCase.preferences

import com.example.apptfgandroid.repository.preferences.JWTokenRepository

class GetToken(
    private val jwTokenRepository: JWTokenRepository
) {
    suspend operator fun invoke(key: String) = jwTokenRepository.getToken(key)
}