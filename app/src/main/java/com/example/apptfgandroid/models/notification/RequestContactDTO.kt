package com.example.apptfgandroid.models.notification

import com.example.apptfgandroid.models.user.UserDTO

data class RequestContactDTO(
    val userRequest: UserDTO,
    val date: String,
)