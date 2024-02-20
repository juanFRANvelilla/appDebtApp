package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.models.ContactRequestDTO
import com.example.apptfgandroid.models.UserDTO
//import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow

class UseCaseManageContact(
    private val repository: RepositoryManageContacts,
    private val manageTokenViewModel: ManageTokenViewModel
//    private val preferencesRepository: PreferencesRepository
) {

//    suspend fun getTokenFlow(): Flow<String?> = preferencesRepository.getNameFlow()

    suspend fun getContacts(): Flow<Set<UserDTO>> = repository.getContacts(manageTokenViewModel.getToken())

    suspend fun getRequest(): Flow<Set<UserDTO>> = repository.getRequest(manageTokenViewModel.getToken())

    suspend fun sendContactRequest(request: ContactRequestDTO): ServerResponseDTO? = repository.sendContactRequest(request, manageTokenViewModel.getToken())

    suspend fun acceptContactRequest(request: ContactRequestDTO): ServerResponseDTO? = repository.acceptContactRequest(request, manageTokenViewModel.getToken())

}