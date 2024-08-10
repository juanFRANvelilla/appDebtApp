package com.example.debtappandroid.ui.screens.currentDebts

import com.example.debtappandroid.models.debt.DebtDTO
import com.example.debtappandroid.models.notification.DebtNotificationDTO

data class CurrentDebtsState(
    val debtList: List<DebtDTO> = emptyList(),
    var payOffDebt: (Int) -> Unit = { _ -> },
    var sendNotification: (DebtNotificationDTO) -> Unit = { _ -> },
) {
    companion object {
        val empty = CurrentDebtsState()
    }
}