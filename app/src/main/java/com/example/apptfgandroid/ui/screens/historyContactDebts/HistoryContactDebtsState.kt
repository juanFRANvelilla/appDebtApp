package com.example.apptfgandroid.ui.screens.historyContactDebts

import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO

data class HistoryContactDebtsState(
    val counterpartyUser: String = "",
    val debtList: List<DebtDTO> = emptyList(),
    var payOffDebt: (Int) -> Unit = { _ -> },
    var sendNotification: (DebtNotificationDTO) -> Unit = { _ -> },
) {
    companion object {
        val empty = HistoryContactDebtsState()
    }
}