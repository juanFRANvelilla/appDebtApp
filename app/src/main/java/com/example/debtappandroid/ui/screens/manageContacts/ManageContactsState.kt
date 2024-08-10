package com.example.debtappandroid.ui.screens.manageContacts

import com.example.debtappandroid.models.user.UserDTO
import com.example.tfgapp.models.ServerResponseDTO

data class ManageContactsState(
    val contacts: Set<UserDTO> = emptySet(),
    var sendContactRequest: (
        String,
        (ServerResponseDTO) -> Unit,
        (Boolean) -> Unit) -> Unit = {_, _, _ -> }
) {
    companion object {
        val empty = ManageContactsState()
    }
}