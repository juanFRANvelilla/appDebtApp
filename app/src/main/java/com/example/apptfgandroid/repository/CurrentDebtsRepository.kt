package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.CurrentDebtsRemoteDataSource
import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentDebtsRepository(
    private val currentDebtsRemoteDataSource: CurrentDebtsRemoteDataSource
) {

    suspend fun getDebts(token: String): Flow<List<DebtDTO>?> = flow{
        val request = currentDebtsRemoteDataSource.getDebts(token)
        emit(request)
    }

    suspend fun payOffDebt(token: String, debtId: Int): ServerResponseDTO?{
        return currentDebtsRemoteDataSource.payOffDebt(token, debtId)
    }

    suspend fun sendNotification(debtNotification: DebtNotificationDTO, token: String): ServerResponseDTO? {
        return currentDebtsRemoteDataSource.sendNotification(debtNotification, token)
    }

}