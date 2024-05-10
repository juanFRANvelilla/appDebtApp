package com.example.apptfgandroid.ui.screens.mainMenu

import com.example.apptfgandroid.models.UserDTO

data class MainMenuState(
    val contactRequest: Set<UserDTO> = emptySet(),
    var deleteToken: () -> Unit = {},
    var acceptContactRequest: (UserDTO) -> Unit = {_ -> }

    ){
    companion object {
        val empty = MainMenuState()
    }
}