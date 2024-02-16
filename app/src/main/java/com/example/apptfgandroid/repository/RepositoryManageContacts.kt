package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.models.ContactRequestDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryManageContacts(
    private val dataSourceManageContacts: DataSourceManageContacts
) {
    suspend fun getContacts(token: String): Flow<Set<UserDTO>> = flow {
        val contacts = dataSourceManageContacts.getContacts(token)
        emit(contacts.toSet())
    }

    suspend fun getRequest(token: String): Flow<Set<UserDTO>> = flow{
        val request = dataSourceManageContacts.getRequest(token)
        emit(request.toSet())
    }


    suspend fun sendContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? {
        return dataSourceManageContacts.sendContactRequest(request, token)
    }

    suspend fun acceptContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? {
        return dataSourceManageContacts.acceptContactRequest(request, token)
    }
}