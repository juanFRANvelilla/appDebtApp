package com.example.apptfgandroid.ui.screens.saveDebt

import com.example.apptfgandroid.models.CreateDebtDTO
import com.example.apptfgandroid.models.UserDTO

data class SaveDebtState(
    val contacts: Set<UserDTO> = emptySet(),
    var saveDebt: (CreateDebtDTO) -> Unit = { }
)