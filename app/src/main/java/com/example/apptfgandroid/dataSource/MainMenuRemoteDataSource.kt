package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
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

    suspend fun getNotifications(token: String): NotificationDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getNotifications()
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