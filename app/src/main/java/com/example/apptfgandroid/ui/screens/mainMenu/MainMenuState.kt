package com.example.apptfgandroid.ui.screens.mainMenu

import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
import com.example.apptfgandroid.models.user.UserDTO

data class MainMenuState(
    val notificationList: NotificationDTO? = NotificationDTO(emptyList()),
    var deleteToken: () -> Unit = {},
    var acceptContactRequest: (UserDTO) -> Unit = { _ -> },
    var balance: BalanceDTO = BalanceDTO.empty
    ){
        companion object {
            val empty = MainMenuState()
        }
    }