package com.example.apptfgandroid.models.notification

import com.example.apptfgandroid.models.user.UserDTO

data class NotificationDTO(
    val requestContactList: List<RequestContactDTO>,
    val debtNotificationList: List<DebtNotificationDTO>
){
    companion object {
        fun empty(): NotificationDTO {
            return NotificationDTO(
                requestContactList = emptyList(),
                debtNotificationList = emptyList()
            )
        }
    }
}