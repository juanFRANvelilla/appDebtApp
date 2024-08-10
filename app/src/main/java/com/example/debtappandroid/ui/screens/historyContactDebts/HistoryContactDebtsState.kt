package com.example.debtappandroid.ui.screens.historyContactDebts

import com.example.debtappandroid.models.debt.DebtDTO
import com.example.debtappandroid.models.notification.DebtNotificationDTO

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