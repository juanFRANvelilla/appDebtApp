package com.example.debtappandroid.useCase

import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.models.debt.CreateDebtDTO
import com.example.debtappandroid.repository.SaveDebtRepository
import com.example.tfgapp.models.ServerResponseDTO

class SaveDebtUseCase(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val saveDebtRepository: SaveDebtRepository
) {
    suspend fun saveDebt(createDebtDTO: CreateDebtDTO): ServerResponseDTO? = saveDebtRepository.saveDebt(manageTokenViewModel.getToken(), createDebtDTO)
}