package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.ApiService
import retrofit2.HttpException
import retrofit2.Retrofit

class DataSourceRegister(
    private val retrofit: Retrofit
){
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }

    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO): ServerResponseDTO {
        try {
            val response: Map<String, Any> = apiService.confirmPhone(phoneValidationDTO)
            return response.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val responseDTO = ConvertResponseToServerResponseDTO(
                        e.response()?.errorBody()?.string()
                    )
                    return responseDTO
                }

                else -> println(e.message)
            }
            return ServerResponseDTO("","")
        }
    }

    suspend fun validatePhone(createUserDTO: CreateUserDTO): ServerResponseDTO {
        val response: Map<String, Any> = apiService.validatePhone(createUserDTO)
        return response.toServerResponseDTO()
    }

}