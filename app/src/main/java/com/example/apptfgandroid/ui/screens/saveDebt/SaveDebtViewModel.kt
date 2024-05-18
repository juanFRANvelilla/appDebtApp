package com.example.apptfgandroid.ui.screens.saveDebt

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.debt.CreateDebtDTO
import com.example.apptfgandroid.useCase.SaveDebtUseCase
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.utils.toCommonMutableStateFlow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveDebtViewModel(
    private val saveDebtUseCase: SaveDebtUseCase,
    private val useCaseManageContact: UseCaseManageContact
):ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(SaveDebtState())
    val state = _state.toCommonMutableStateFlow()

    init {
        getContacts()
        _state.value.saveDebt = { createDebtDTO -> saveDebt(createDebtDTO) }
    }


    private fun getContacts(){
        viewModelScope.launch {
            useCaseManageContact.getContacts().collect {contacts ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            contacts = contacts
                        )
                    }
                }
            }
        }
    }

    private fun saveDebt(createDebtDTO: CreateDebtDTO){
        viewModelScope.launch {
            saveDebtUseCase.saveDebt(createDebtDTO)
            }
        }
}