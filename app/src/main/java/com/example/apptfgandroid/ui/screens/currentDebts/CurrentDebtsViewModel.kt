package com.example.apptfgandroid.ui.screens.currentDebts

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.toCommonMutableStateFlow
import com.example.apptfgandroid.ui.screens.mainMenu.MainMenuState
import com.example.apptfgandroid.useCase.CurrentDebtsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrentDebtsViewModel(
    private val currentDebtsUseCase: CurrentDebtsUseCase

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(CurrentDebtsState())
    val state = _state.toCommonMutableStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getDebts()
        }
    }

    private suspend fun getDebts(){
        viewModelScope.launch(Dispatchers.Main) {
            currentDebtsUseCase.getDebts().collect {debts ->
                withContext(Dispatchers.Main) {
                    if(debts != null){
                        _state.update {
                            it.copy(
                                debtList = debts
                            )
                        }
                    }
                }
            }
        }
    }
}