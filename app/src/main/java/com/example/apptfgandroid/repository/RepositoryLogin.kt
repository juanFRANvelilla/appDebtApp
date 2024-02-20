package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceLogin
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.tfgapp.models.ServerResponseDTO

class RepositoryLogin(
    private val dataSourceLogin: DataSourceLogin,
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO): ServerResponseDTO = dataSourceLogin.doLogin(loginRequest)

}