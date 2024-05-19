package com.example.apptfgandroid.ui.screens.currentDebts

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.apptfgandroid.useCase.CurrentDebtsUseCase
import com.example.apptfgandroid.utils.toCommonMutableStateFlow
import com.example.tfgapp.models.ServerResponseDTO
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
        _state.value.payOffDebt = { debtId -> payOffDebt(debtId) }
        _state.value.sendNotification = { debtNotification -> sendNotification(debtNotification) }
    }

    private fun sendNotification(debtNotification: DebtNotificationDTO){
        println("Sending notification")
        viewModelScope.launch(Dispatchers.Main) {
            currentDebtsUseCase.sendNotification(debtNotification)
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

    private fun payOffDebt(debtId: Int){
        viewModelScope.launch(Dispatchers.Main) {
            val responseDTO: ServerResponseDTO? = currentDebtsUseCase.payOffDebt(debtId)
            if(responseDTO?.status=="response"){
                getDebts()
            }
        }
    }
}