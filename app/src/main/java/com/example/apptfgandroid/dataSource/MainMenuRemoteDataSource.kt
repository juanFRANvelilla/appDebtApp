package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.BalanceDTO
import com.example.tfgapp.service.RetrofitService
import retrofit2.HttpException

class MainMenuRemoteDataSource {
    suspend fun getBalance(token: String): BalanceDTO? {
        val apiService = RetrofitService.contactsCallsJwt(token)
        try {
            val responseServer: BalanceDTO = apiService.getBalance()
            println("Respuesta getBalance: ${responseServer.toString()}")
            return responseServer
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Respuesta getBalance ERROR: $errorMessage")
                    return null
                }
                else -> println(e.message)
            }
        }
        return null
    }
}