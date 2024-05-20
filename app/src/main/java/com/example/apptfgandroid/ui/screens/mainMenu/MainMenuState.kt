package com.example.apptfgandroid.ui.screens.mainMenu

import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
import com.example.apptfgandroid.models.user.UserDTO

data class MainMenuState(
    val notificationListSorted: List<Pair<String, Any>> = mutableListOf(),
    var deleteToken: () -> Unit = {},
    var acceptContactRequest: (UserDTO) -> Unit = { _ -> },
    var balance: BalanceDTO = BalanceDTO.empty,
    var removeNotification: () -> Unit = { }
    ){
        companion object {
            val empty = MainMenuState()
        }
    }