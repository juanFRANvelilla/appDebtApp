package com.example.apptfgandroid.ui.screens.currentDebts

import com.example.apptfgandroid.models.debt.DebtDTO

data class CurrentDebtsState(
    val debtList: List<DebtDTO> = emptyList(),
    var payOffDebt: (Int) -> Unit = { _ -> },
) {
    companion object {
        val empty = CurrentDebtsState()
    }
}