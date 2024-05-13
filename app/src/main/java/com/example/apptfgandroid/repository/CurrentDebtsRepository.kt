package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.CurrentDebtsRemoteDataSource
import com.example.apptfgandroid.models.DebtDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrentDebtsRepository(
    private val currentDebtsRemoteDataSource: CurrentDebtsRemoteDataSource
) {

    suspend fun getDebts(token: String): Flow<List<DebtDTO>?> = flow{
        val request = currentDebtsRemoteDataSource.getDebts(token)
        emit(request)
    }

}