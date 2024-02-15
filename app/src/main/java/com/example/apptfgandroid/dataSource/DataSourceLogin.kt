package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.tfgapp.service.ApiService
import retrofit2.Retrofit

class DataSourceLogin(
    private val retrofit: Retrofit
) {
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun doLogin(loginRequest: LoginRequestDTO): String = apiService.login(loginRequest).message

}