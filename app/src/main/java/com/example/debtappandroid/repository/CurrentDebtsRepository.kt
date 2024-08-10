package com.example.debtappandroid.repository

import com.example.debtappandroid.dataSource.CurrentDebtsRemoteDataSource
import com.example.debtappandroid.models.debt.DebtDTO
import com.example.debtappandroid.models.notification.DebtNotificationDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentDebtsRepository(
    private val currentDebtsRemoteDataSource: CurrentDebtsRemoteDataSource
) {

    suspend fun getCurrentDebts(token: String): Flow<List<DebtDTO>?> = flow{
        val request = currentDebtsRemoteDataSource.getCurrentDebts(token)
        emit(request)
    }

    suspend fun getHistoricalDebts(token: String, counterpartyUser: String): Flow<List<DebtDTO>?> = flow{
        val request = currentDebtsRemoteDataSource.getHistoricalDebts(token, counterpartyUser)
        emit(request)
    }

    suspend fun payOffDebt(token: String, debtId: Int): ServerResponseDTO?{
        return currentDebtsRemoteDataSource.payOffDebt(token, debtId)
    }

    suspend fun sendNotification(debtNotification: DebtNotificationDTO, token: String): ServerResponseDTO? {
        return currentDebtsRemoteDataSource.sendNotification(debtNotification, token)
    }

}