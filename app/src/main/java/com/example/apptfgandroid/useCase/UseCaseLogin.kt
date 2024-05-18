package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.models.access.LoginRequestDTO
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.LoginRepository

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