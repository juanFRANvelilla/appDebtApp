package com.example.debtappandroid.models.balance

data class BalanceDTO (
    val owe: Double,
    val owed: Double
) {
    companion object {
        val empty = BalanceDTO(0.0, 0.0)
    }
}