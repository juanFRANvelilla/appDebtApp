package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.repository.RepositoryManageContacts
import kotlinx.coroutines.flow.Flow

class UseCaseManageContact(
    private val repository: RepositoryManageContacts
) {

    suspend fun getUsers(): Flow<Set<UserDTO>> = repository.getContacts()


}