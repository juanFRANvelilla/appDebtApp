package com.example.debtappandroid.dataSource

import com.example.debtappandroid.models.user.CreateUserDTO
import com.example.debtappandroid.models.access.PhoneValidationDTO
import com.example.debtappandroid.models.access.SmsCode
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.ApiService
import retrofit2.HttpException
import retrofit2.Retrofit

class RegisterRemoteDataSource(
    private val urlFastApi: Retrofit,
    private val urlBackend: Retrofit
){
    private val fastApiService: ApiService by lazy {
        urlFastApi.create(ApiService::class.java)
    }


    private val backendApiService: ApiService by lazy {
        urlBackend.create(ApiService::class.java)
    }


    suspend fun sendSmsCode(smsCode: SmsCode) {
        try {
            fastApiService.sendSms(smsCode)
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Respuesta de error $errorMessage")
                }
                else -> println(e.message)
            }
        }
    }



    suspend fun confirmPhone(phoneValidationDTO: PhoneValidationDTO): ServerResponseDTO {
        try {
            val response: Map<String, Any> = backendApiService.confirmPhone(phoneValidationDTO)
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
        val response: Map<String, Any> = backendApiService.validatePhone(createUserDTO)
        return response.toServerResponseDTO()
    }

}