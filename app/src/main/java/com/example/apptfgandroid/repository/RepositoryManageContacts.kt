package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.DataSourceManageContacts
import com.example.apptfgandroid.models.UserDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryManageContacts(
    private val dataSourceManageContacts: DataSourceManageContacts
) {
    suspend fun getContacts(): Flow<Set<UserDTO>> = flow {
        try {
            val contacts = dataSourceManageContacts.getUsers()
            emit(contacts.toSet())
        } catch (e: Exception) {
            emit(emptySet())
        }
    }
}