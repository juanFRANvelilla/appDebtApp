package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.models.LoginRequestDTO
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.RepositoryLogin

class UseCaseLogin(
    private val repositoryLogin: RepositoryLogin,
    private val manageTokenViewModel: ManageTokenViewModel
//    private val preferencesRepository: PreferencesRepository
) {
    suspend fun doLogin(loginRequest: LoginRequestDTO){
        val token = repositoryLogin.doLogin(loginRequest)
        manageTokenViewModel.setToken(token)
//        preferencesRepository.saveToken(token)
    }

}