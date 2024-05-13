package com.example.apptfgandroid.models

import java.time.LocalDate

data class DebtDTO(
    val isCreditor: Boolean,
    val counterpartyUser: UserDTO,
    val amount: Double,
    val date: LocalDate,
    val description: String,
    val isPaid: Boolean
)