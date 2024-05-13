package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.BalanceDTO
import com.example.apptfgandroid.models.DebtDTO
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException

class CurrentDebtsRemoteDataSource {
    suspend fun getDebts(token: String): List<DebtDTO>? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            return apiService.getCurrentDebts()

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