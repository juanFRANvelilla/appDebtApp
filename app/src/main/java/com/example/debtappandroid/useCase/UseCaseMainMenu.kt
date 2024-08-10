package com.example.debtappandroid.useCase

import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.models.notification.DebtNotificationDTO
import com.example.debtappandroid.repository.MainMenuRepository
import com.example.tfgapp.models.ServerResponseDTO


class UseCaseMainMenu(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val mainMenuRepository: MainMenuRepository
) {
    fun deleteToken() = manageTokenViewModel.setToken("")
    suspend fun getBalance() = mainMenuRepository.getBalance(manageTokenViewModel.getToken())

    suspend fun getNotifications() = mainMenuRepository.getNotifications(manageTokenViewModel.getToken())

    suspend fun removeNotification(debtNotificationDTO: DebtNotificationDTO): ServerResponseDTO? = mainMenuRepository.removeNotification(debtNotificationDTO, manageTokenViewModel.getToken())
}