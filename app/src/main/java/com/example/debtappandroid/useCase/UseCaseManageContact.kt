package com.example.debtappandroid.useCase

import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.models.user.UserDTO
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.debtappandroid.repository.ManageContactsRepository
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow

class UseCaseManageContact(
    private val repository: ManageContactsRepository,
    private val manageTokenViewModel: ManageTokenViewModel
//    private val preferencesRepository: PreferencesRepository
) {

//    suspend fun getTokenFlow(): Flow<String?> = preferencesRepository.getNameFlow()

    suspend fun getContacts(): Flow<Set<UserDTO>> = repository.getContacts(manageTokenViewModel.getToken())

    suspend fun getRequest(): Flow<Set<UserDTO>> = repository.getRequest(manageTokenViewModel.getToken())

    suspend fun sendContactRequest(contactRequest: String): ServerResponseDTO? = repository.sendContactRequest(contactRequest, manageTokenViewModel.getToken())

    suspend fun acceptContactRequest(contactRequest: String): ServerResponseDTO? = repository.acceptContactRequest(contactRequest, manageTokenViewModel.getToken())

}