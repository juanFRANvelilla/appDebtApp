package com.example.debtappandroid.ui.screens.saveDebt

import com.example.debtappandroid.models.debt.CreateDebtDTO
import com.example.debtappandroid.models.user.UserDTO

data class SaveDebtState(
    val contacts: Set<UserDTO> = emptySet(),
    var saveDebt: (CreateDebtDTO) -> Unit = { }
)