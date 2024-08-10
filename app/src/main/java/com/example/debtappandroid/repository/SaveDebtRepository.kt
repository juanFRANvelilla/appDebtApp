package com.example.debtappandroid.repository

import com.example.debtappandroid.dataSource.SaveDebtRemoteDataSource
import com.example.debtappandroid.models.debt.CreateDebtDTO
import com.example.tfgapp.models.ServerResponseDTO

class SaveDebtRepository(
    private val saveDebtRemoteDataSource: SaveDebtRemoteDataSource
) {
    suspend fun saveDebt(token: String, createDebtDTO: CreateDebtDTO): ServerResponseDTO? = saveDebtRemoteDataSource.saveDebt(token, createDebtDTO)
}