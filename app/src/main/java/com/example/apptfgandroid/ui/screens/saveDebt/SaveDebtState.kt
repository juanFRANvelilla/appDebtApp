package com.example.apptfgandroid.ui.screens.saveDebt

import com.example.apptfgandroid.models.CreateDebtDTO

data class SaveDebtState(
    var saveDebt: (CreateDebtDTO) -> Unit = { }
)