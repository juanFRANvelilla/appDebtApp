package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.MainMenuRemoteDataSource
import com.example.apptfgandroid.models.BalanceDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainMenuRepository(
    private val mainMenuRemoteDataSource: MainMenuRemoteDataSource
) {
    suspend fun getBalance(token: String): Flow<BalanceDTO?> = flow{
        val request = mainMenuRemoteDataSource.getBalance(token)
        emit(request)
    }
}