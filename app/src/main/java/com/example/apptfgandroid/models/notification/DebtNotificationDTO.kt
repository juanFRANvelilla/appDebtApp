package com.example.apptfgandroid.models.notification

import com.example.apptfgandroid.models.debt.DebtDTO

data class DebtNotificationDTO(
    val debt: DebtDTO,
    val date: String,
)