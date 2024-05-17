package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.repository.CurrentDebtsRepository
import com.example.tfgapp.models.ServerResponseDTO

class CurrentDebtsUseCase(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val currentDebtsRepository: CurrentDebtsRepository
) {
    suspend fun getDebts() = currentDebtsRepository.getDebts(manageTokenViewModel.getToken())

    suspend fun payOffDebt(debtId: Int): ServerResponseDTO? = currentDebtsRepository.payOffDebt(manageTokenViewModel.getToken(), debtId)
}