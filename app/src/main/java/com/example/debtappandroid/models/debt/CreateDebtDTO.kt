package com.example.debtappandroid.models.debt

data class CreateDebtDTO(
    val debtorUsername: String,
    val amount: Double,
    val description: String,
    val isPaid: Boolean
)