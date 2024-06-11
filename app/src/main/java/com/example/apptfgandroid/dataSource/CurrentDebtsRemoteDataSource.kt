package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException

class CurrentDebtsRemoteDataSource {
    suspend fun getCurrentDebts(token: String): List<DebtDTO>? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getCurrentDebts()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Error en getCurrentDebts: $errorMessage")
                    return null
                }
                else -> println(e.message)
            }
        }
        return null
    }


    suspend fun getHistoricalDebts(token: String, counterpartyUser: String): List<DebtDTO>? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getHistoricalDebts(counterpartyUser)

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Error en getCurrentDebts: $errorMessage")
                    return null
                }
                else -> println(e.message)
            }
        }
        return null
    }

    suspend fun payOffDebt(token: String, debtId: Int): ServerResponseDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            val responseServer: Map<String, Any> = apiService.payOffDebt(debtId)
            return responseServer.toServerResponseDTO()

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Error en payOffDebt: $errorMessage")
                }

                else -> println(e.message)
            }
        }
        return null
    }

    suspend fun sendNotification(debtNotification: DebtNotificationDTO, token: String): ServerResponseDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            val response = apiService.createDebtNotification(debtNotification)
            return response.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    return null
                }
                else -> println(e.message)
            }
        }
        return null
    }
}