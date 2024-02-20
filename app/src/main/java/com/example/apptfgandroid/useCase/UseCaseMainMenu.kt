package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel

//import com.example.apptfgandroid.repository.PreferencesRepository

class UseCaseMainMenu(
    private val manageTokenViewModel: ManageTokenViewModel
) {
    suspend fun deleteToken() = manageTokenViewModel.setToken("")
}