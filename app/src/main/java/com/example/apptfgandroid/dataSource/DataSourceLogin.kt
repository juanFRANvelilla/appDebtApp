package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.service.ApiService
import retrofit2.Retrofit

class DataSourceLogin(
    private val appViewModel: AppViewModel,
    private val retrofit: Retrofit
) {

    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun doLogin(loginRequest: LoginRequestDTO){
        val response: ServerResponseDTO = apiService.login(loginRequest)
        appViewModel.setToken(response.message)
    }

}