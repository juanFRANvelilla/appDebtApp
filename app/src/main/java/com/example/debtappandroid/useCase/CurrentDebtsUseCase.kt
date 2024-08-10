package com.example.debtappandroid.useCase

import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.models.notification.DebtNotificationDTO
import com.example.debtappandroid.repository.CurrentDebtsRepository
import com.example.tfgapp.models.ServerResponseDTO

class CurrentDebtsUseCase(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val currentDebtsRepository: CurrentDebtsRepository
) {
    suspend fun getCurrentDebts() = currentDebtsRepository.getCurrentDebts(manageTokenViewModel.getToken())

    suspend fun getHistoricalDebts(counterpartyUser: String) = currentDebtsRepository.getHistoricalDebts(manageTokenViewModel.getToken(), counterpartyUser)

    suspend fun payOffDebt(debtId: Int): ServerResponseDTO? = currentDebtsRepository.payOffDebt(manageTokenViewModel.getToken(), debtId)

    suspend fun sendNotification(debtNotification: DebtNotificationDTO) = currentDebtsRepository.sendNotification(debtNotification, manageTokenViewModel.getToken())

}