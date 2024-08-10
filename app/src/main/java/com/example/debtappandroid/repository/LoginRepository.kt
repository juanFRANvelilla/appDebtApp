package com.example.debtappandroid.repository

import com.example.debtappandroid.dataSource.LoginRemoteDataSource
import com.example.debtappandroid.models.access.LoginRequestDTO
import com.example.tfgapp.models.ServerResponseDTO

class LoginRepository(
    private val loginRemoteDataSource: LoginRemoteDataSource,
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO): ServerResponseDTO = loginRemoteDataSource.doLogin(loginRequest)

}