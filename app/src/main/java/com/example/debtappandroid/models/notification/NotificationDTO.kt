package com.example.debtappandroid.models.notification

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