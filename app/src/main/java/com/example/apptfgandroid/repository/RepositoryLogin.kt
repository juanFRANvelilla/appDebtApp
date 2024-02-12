package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceLogin
import com.example.apptfgandroid.models.LoginRequestDTO

class RepositoryLogin(
    private val dataSourceLogin: DataSourceLogin
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO){
        dataSourceLogin.doLogin(loginRequest)
    }

}