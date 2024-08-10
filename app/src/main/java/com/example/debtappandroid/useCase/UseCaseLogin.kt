package com.example.debtappandroid.useCase

import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.models.access.LoginRequestDTO
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.debtappandroid.repository.LoginRepository

class UseCaseLogin(
    private val loginRepository: LoginRepository,
    private val manageTokenViewModel: ManageTokenViewModel
//    private val preferencesRepository: PreferencesRepository
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO){
        val responseLogin = loginRepository.doLogin(loginRequest)
        manageTokenViewModel.setToken(responseLogin.message)
//        preferencesRepository.saveToken(responseLogin.message)
    }

}