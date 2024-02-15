package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.models.RequestContactDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryManageContacts(
    private val dataSourceManageContacts: DataSourceManageContacts
) {
    suspend fun getContacts(): Flow<Set<UserDTO>> = flow {
        val contacts = dataSourceManageContacts.getContacts()
        emit(contacts.toSet())
    }

    suspend fun getRequest(): Flow<Set<UserDTO>> = flow{
        val request = dataSourceManageContacts.getRequest()
        emit(request.toSet())
    }


    suspend fun sendContactRequest(request: RequestContactDTO): ServerResponseDTO? {
        return dataSourceManageContacts.sendContactRequest(request)
    }
}