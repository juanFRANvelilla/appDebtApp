package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.repository.RepositoryLogin

class UseCaseLogin(
    private val repositoryLogin: RepositoryLogin
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO){
        repositoryLogin.doLogin(loginRequest)
    }

//    suspend fun saveToken(token: String){
//        repositoryLogin.saveToken(token)
//    }

//    fun getNameFlow(): Flow<String?> {
//        return repositoryLogin.getNameFlow()
//    }

}