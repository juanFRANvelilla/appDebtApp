package com.example.debtappandroid.dataSource

import com.example.debtappandroid.models.balance.BalanceDTO
import com.example.debtappandroid.models.notification.DebtNotificationDTO
import com.example.debtappandroid.models.notification.NotificationDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException

class MainMenuRemoteDataSource {
    suspend fun getBalance(token: String): BalanceDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getBalance()
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

    suspend fun getNotifications(token: String): NotificationDTO {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getNotifications()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println(errorMessage)
                    return NotificationDTO.empty()
                }
                else -> println(e.message)
            }
        }
        return NotificationDTO.empty()
    }

    suspend fun removeNotification(debtNotificationDTO: DebtNotificationDTO, token: String): ServerResponseDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            val response = apiService.removeNotification(debtNotificationDTO)
            return response.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println(errorMessage)
                    return null
                }
                else -> println(e.message)
            }
        }
        return null
    }

}