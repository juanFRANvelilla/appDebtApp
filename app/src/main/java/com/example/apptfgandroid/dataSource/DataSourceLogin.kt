package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.ApiService
import retrofit2.Retrofit

class DataSourceLogin(
    private val retrofit: Retrofit
) {
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun doLogin(loginRequest: LoginRequestDTO): ServerResponseDTO {
        val response = apiService.login(loginRequest)
        println(response)
        return response.toServerResponseDTO()
    }
}