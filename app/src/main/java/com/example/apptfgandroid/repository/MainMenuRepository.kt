package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.MainMenuRemoteDataSource
import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MainMenuRepository(
    private val mainMenuRemoteDataSource: MainMenuRemoteDataSource
) {
    suspend fun getBalance(token: String): Flow<BalanceDTO?> = flow{
        val request = mainMenuRemoteDataSource.getBalance(token)
        emit(request)
    }

    suspend fun getNotifications(token: String): Flow<NotificationDTO> = flow{
        val request = mainMenuRemoteDataSource.getNotifications(token)
        emit(request)
    }

    suspend fun removeNotification(debtNotificationDTO: DebtNotificationDTO, token: String): ServerResponseDTO? {
        return mainMenuRemoteDataSource.removeNotification(debtNotificationDTO, token)
    }
}