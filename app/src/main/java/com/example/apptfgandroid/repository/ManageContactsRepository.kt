package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.ManageContactsRemoteDataSource
import com.example.apptfgandroid.models.user.UserDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ManageContactsRepository(
    private val manageContactsRemoteDataSource: ManageContactsRemoteDataSource
) {
    suspend fun getContacts(token: String): Flow<Set<UserDTO>> = flow {
        val contacts = manageContactsRemoteDataSource.getContacts(token)
        emit(contacts.toSet())
    }

    suspend fun getRequest(token: String): Flow<Set<UserDTO>> = flow{
        val request = manageContactsRemoteDataSource.getRequest(token)
        emit(request.toSet())
    }


    suspend fun sendContactRequest(contactRequest: String, token: String): ServerResponseDTO? {
        return manageContactsRemoteDataSource.sendContactRequest(contactRequest, token)
    }

    suspend fun acceptContactRequest(contactRequest: String, token: String): ServerResponseDTO? {
        return manageContactsRemoteDataSource.acceptContactRequest(contactRequest, token)
    }
}