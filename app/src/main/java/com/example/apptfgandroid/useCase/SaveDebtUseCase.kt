package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.apptfgandroid.repository.SaveDebtRepository
import com.example.tfgapp.models.ServerResponseDTO

class SaveDebtUseCase(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val saveDebtRepository: SaveDebtRepository
) {
    suspend fun saveDebt(createDebtDTO: CreateDebtDTO): ServerResponseDTO? = saveDebtRepository.saveDebt(manageTokenViewModel.getToken(), createDebtDTO)
}