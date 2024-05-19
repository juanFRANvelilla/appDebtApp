package com.example.apptfgandroid.ui.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.models.user.UserDTO
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
import com.example.apptfgandroid.models.notification.RequestContactDTO
import com.example.apptfgandroid.ui.screens.mainMenu.MainMenuState

//@Preview
//@Composable
//fun sdfdsfsssss(){
//    ShowNotifications(onDismiss = { /*TODO*/ }, request = getUsersExample().toList())
//}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowNotifications(
    onDismiss: () -> Unit,
    onAcceptRequest: (UserDTO) -> Unit,
    state: MainMenuState
) {
    val notifications = state.notificationListSorted
    val numberOfNofifications = notifications.size

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(14.dp)
                )
                .height(if(numberOfNofifications < 3) 120.dp * numberOfNofifications else 360.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                items(notifications) { notification ->
                    Column {
                        when (notification.second) {
                            is RequestContactDTO -> {
                                Text(text = (notification.second as RequestContactDTO).userRequest.username, color = Color.Red)
                                Text(text = "${(notification.second as RequestContactDTO).userRequest.firstName} ${(notification.second as RequestContactDTO).userRequest.lastName} ha solicitado ser tu contacto")
                                Button(
                                    onClick = {
                                        onAcceptRequest((notification.second as RequestContactDTO).userRequest)
                                        state.removeNotification()
                                    }
                                )
                                {
                                    Text(text = "ACEPTAR")
                                }
                            }
                            else -> {
                                Text("Notificacion de deuda")
                                Text(text = (notification.second as DebtNotificationDTO).debt.description, color = Color.Red)
                            }
                        }

                    }
                }
            }
        }
    }
}

