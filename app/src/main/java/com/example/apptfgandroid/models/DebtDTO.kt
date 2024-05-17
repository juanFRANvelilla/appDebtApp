package com.example.apptfgandroid.models

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