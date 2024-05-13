package com.example.apptfgandroid.ui.screens.currentDebts

import com.example.apptfgandroid.models.DebtDTO

data class CurrentDebtsState(
    val debtList: List<DebtDTO> = emptyList()
) {
    companion object {
        val empty = CurrentDebtsState()
    }
}