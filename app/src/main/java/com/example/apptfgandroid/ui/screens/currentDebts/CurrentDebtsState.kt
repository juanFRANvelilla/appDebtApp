package com.example.apptfgandroid.ui.screens.currentDebts

import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO

data class CurrentDebtsState(
    val debtList: List<DebtDTO> = emptyList(),
    var payOffDebt: (Int) -> Unit = { _ -> },
    var sendNotification: (DebtNotificationDTO) -> Unit = { _ -> },
) {
    companion object {
        val empty = CurrentDebtsState()
    }
}