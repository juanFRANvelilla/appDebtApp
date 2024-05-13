package com.example.apptfgandroid.ui.screens.mainMenu

import com.example.apptfgandroid.models.BalanceDTO
import com.example.apptfgandroid.models.UserDTO

data class MainMenuState(
    val contactRequest: Set<UserDTO> = emptySet(),
    var deleteToken: () -> Unit = {},
    var acceptContactRequest: (UserDTO) -> Unit = {_ -> },
    var balance: BalanceDTO = BalanceDTO.empty
    ){
        companion object {
            val empty = MainMenuState()
        }
    }