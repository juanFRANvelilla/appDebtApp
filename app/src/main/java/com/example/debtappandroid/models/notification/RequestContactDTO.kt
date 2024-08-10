package com.example.debtappandroid.models.notification

import com.example.debtappandroid.models.user.UserDTO

data class RequestContactDTO(
    val userRequest: UserDTO,
    val date: String,
)