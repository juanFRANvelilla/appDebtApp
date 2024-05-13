package com.example.apptfgandroid.useCase

import com.example.apptfgandroid.commonViewModel.ManageTokenViewModel
import com.example.apptfgandroid.repository.CurrentDebtsRepository

class CurrentDebtsUseCase(
    private val manageTokenViewModel: ManageTokenViewModel,
    private val currentDebtsRepository: CurrentDebtsRepository
) {
    suspend fun getDebts() = currentDebtsRepository.getDebts(manageTokenViewModel.getToken())
}