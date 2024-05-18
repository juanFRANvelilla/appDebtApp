package com.example.apptfgandroid.ui.screens.saveDebt

import com.example.apptfgandroid.models.debt.CreateDebtDTO
import com.example.apptfgandroid.models.user.UserDTO

data class SaveDebtState(
    val contacts: Set<UserDTO> = emptySet(),
    var saveDebt: (CreateDebtDTO) -> Unit = { }
)