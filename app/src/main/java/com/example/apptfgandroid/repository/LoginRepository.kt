package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.LoginRemoteDataSource
import com.example.apptfgandroid.models.access.LoginRequestDTO
import com.example.tfgapp.models.ServerResponseDTO

class LoginRepository(
    private val loginRemoteDataSource: LoginRemoteDataSource,
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO): ServerResponseDTO = loginRemoteDataSource.doLogin(loginRequest)

}