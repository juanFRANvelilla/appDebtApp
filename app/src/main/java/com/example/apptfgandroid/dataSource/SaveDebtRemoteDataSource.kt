package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException

class SaveDebtRemoteDataSource {
    suspend fun saveDebt(token: String, createDebtDTO: CreateDebtDTO): ServerResponseDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            val responseServer: Map<String, Any> = apiService.saveDebt(createDebtDTO)
            return responseServer.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Respuesta de error $errorMessage")
                    if (!errorMessage.equals("")) {
                        val responseDTO = ConvertResponseToServerResponseDTO(errorMessage)
                        return responseDTO
                    } else {
                        return null
                    }
                }
                else -> println(e.message)
            }
        }
        return null
    }
}