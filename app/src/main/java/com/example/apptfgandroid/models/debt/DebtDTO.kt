package com.example.apptfgandroid.models.debt

import com.example.apptfgandroid.models.user.UserDTO
import com.google.gson.annotations.SerializedName


data class DebtDTO(
    val id: Int,
    @SerializedName("creditor")
    val isCreditor: Boolean,
    val counterpartyUser: UserDTO,
    val amount: Double,
    val date: String,
    val description: String,
    val isPaid: Boolean
)