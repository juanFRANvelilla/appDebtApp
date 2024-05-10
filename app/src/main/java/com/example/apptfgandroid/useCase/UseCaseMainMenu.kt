package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.repository.MainMenuRepository


class UseCaseMainMenu(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val mainMenuRepository: MainMenuRepository
) {
    fun deleteToken() = manageTokenViewModel.setToken("")
    suspend fun getBalance() = mainMenuRepository.getBalance(manageTokenViewModel.getToken())
}