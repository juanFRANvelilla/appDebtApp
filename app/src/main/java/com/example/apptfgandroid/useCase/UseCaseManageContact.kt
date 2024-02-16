package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.ContactRequestDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.repository.PreferencesRepository
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow

class UseCaseManageContact(
    private val repository: RepositoryManageContacts,
    private val preferencesRepository: PreferencesRepository
) {

    suspend fun getTokenFlow(): Flow<String?> = preferencesRepository.getNameFlow()

    suspend fun getContacts(token: String): Flow<Set<UserDTO>> = repository.getContacts(token)

    suspend fun getRequest(token: String): Flow<Set<UserDTO>> = repository.getRequest(token)

    suspend fun sendContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? = repository.sendContactRequest(request, token)

    suspend fun acceptContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? = repository.acceptContactRequest(request, token)

}