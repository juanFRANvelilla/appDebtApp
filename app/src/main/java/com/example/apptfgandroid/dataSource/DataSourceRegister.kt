package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.ApiService
import retrofit2.Retrofit

class DataSourceRegister(
    private val retrofit: Retrofit
){
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO){
        val response = apiService.confirmPhone(phoneValidationDTO)
    }

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO{
        val response : Map<String, Any> = apiService.validatePhone(createUserDTO)
        return response.toServerResponseDTO()
    }

}