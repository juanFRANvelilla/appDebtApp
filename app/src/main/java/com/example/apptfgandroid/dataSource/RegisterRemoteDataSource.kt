package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.user.CreateUserDTO
import com.example.apptfgandroid.models.access.PhoneValidationDTO
import com.example.apptfgandroid.models.access.SmsCode
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.ApiService
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException
import retrofit2.Retrofit

class RegisterRemoteDataSource(
    private val retrofit: Retrofit,
//    private val retrofitFastApi: Retrofit
){
    private val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }


    suspend fun sendSmsCode(smsCode: SmsCode) {
        val apiService: ApiService by lazy {
            RetrofitService.fastApiCalls().create(ApiService::class.java)
        }

        try {
            apiService.sendSms(smsCode)
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