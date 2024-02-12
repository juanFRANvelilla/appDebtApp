package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.RequestContactDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.repository.RepositoryManageContacts
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow

class UseCaseManageContact(
    private val repository: RepositoryManageContacts
) {

    suspend fun getUsers(): Flow<Set<UserDTO>> = repository.getContacts()

    suspend fun sendContactRequest(request: RequestContactDTO): ServerResponseDTO? = repository.sendContactRequest(request)


}