package com.example.debtappandroid.models.notification

import com.example.debtappandroid.models.debt.DebtDTO

data class DebtNotificationDTO(
    val debt: DebtDTO,
    val date: String,
)