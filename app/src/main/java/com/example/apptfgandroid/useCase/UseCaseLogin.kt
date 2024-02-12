package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.repository.RepositoryLogin

class UseCaseLogin(
    private val repositoryLogin: RepositoryLogin
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO){
        repositoryLogin.doLogin(loginRequest)
    }
}