package com.example.apptfgandroid.ui.screens.mainMenu

import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.user.UserDTO

data class MainMenuState(
    val contactRequest: Set<UserDTO> = emptySet(),
    var deleteToken: () -> Unit = {},
    var acceptContactRequest: (UserDTO) -> Unit = { _ -> },
    var balance: BalanceDTO = BalanceDTO.empty
    ){
        companion object {
            val empty = MainMenuState()
        }
    }