package com.example.apptfgandroid.ui.screens.saveDebt

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.apptfgandroid.toCommonMutableStateFlow
import com.example.apptfgandroid.useCase.SaveDebtUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SaveDebtViewModel(
    private val saveDebtUseCase: SaveDebtUseCase
):ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SaveDebtState())
    val state = _state.toCommonMutableStateFlow()

    init {
        _state.value.saveDebt = { createDebtDTO -> saveDebt(createDebtDTO) }
    }

    private fun saveDebt(createDebtDTO: CreateDebtDTO){
        viewModelScope.launch {
            saveDebtUseCase.saveDebt(createDebtDTO)
            }
        }
}