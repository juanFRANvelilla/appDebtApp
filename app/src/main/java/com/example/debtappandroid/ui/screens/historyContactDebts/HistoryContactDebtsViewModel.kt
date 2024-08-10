package com.example.debtappandroid.ui.screens.historyContactDebts

import androidx.lifecycle.ViewModel
import com.example.debtappandroid.models.notification.DebtNotificationDTO
import com.example.debtappandroid.useCase.CurrentDebtsUseCase
import com.example.debtappandroid.utils.toCommonMutableStateFlow
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryContactDebtsViewModel(
    private val currentDebtsUseCase: CurrentDebtsUseCase,
    counterpartyUser: String

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(HistoryContactDebtsState())
    val state = _state.toCommonMutableStateFlow()


    init {
        _state.update {
            it.copy(
                counterpartyUser = counterpartyUser
            )
        }
        viewModelScope.launch(Dispatchers.Main) {
            getHistoricalDebts()
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

    private suspend fun getHistoricalDebts() {
        viewModelScope.launch(Dispatchers.Main) {
            currentDebtsUseCase.getHistoricalDebts(_state.value.counterpartyUser).collect {debts ->
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
                getHistoricalDebts()
            }
        }
    }
}