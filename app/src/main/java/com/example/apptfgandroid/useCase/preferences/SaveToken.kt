package com.example.apptfgandroid.useCase.preferences

import com.example.apptfgandroid.repository.preferences.JWTokenRepository

class SaveToken(
    private val jwTokenRepository: JWTokenRepository
) {
    suspend operator fun invoke(key: String, value: String){
        jwTokenRepository.saveToken(key, value)
    }
}