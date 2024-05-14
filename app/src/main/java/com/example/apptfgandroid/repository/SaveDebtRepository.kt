package com.example.apptfgandroid.repository

import com.example.apptfgandroid.dataSource.SaveDebtRemoteDataSource
import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.tfgapp.models.ServerResponseDTO

class SaveDebtRepository(
    private val saveDebtRemoteDataSource: SaveDebtRemoteDataSource
) {
    suspend fun saveDebt(token: String, createDebtDTO: CreateDebtDTO): ServerResponseDTO? = saveDebtRemoteDataSource.saveDebt(token, createDebtDTO)
}