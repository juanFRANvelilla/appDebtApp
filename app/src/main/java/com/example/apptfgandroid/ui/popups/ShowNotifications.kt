package com.example.apptfgandroid.ui.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
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
                .height(if (numberOfNofifications < 3) 120.dp * numberOfNofifications else 360.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(6.dp)
            ) {
                items(notifications) { notification ->
                    Column {
                        when (notification.second) {
                            is RequestContactDTO -> {
                                RequestContactNotificationCard(
                                    requestContact = (notification.second as RequestContactDTO),
                                    onAcceptRequest = {
                                        onAcceptRequest(it)
                                    }
                                )
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

@Composable
fun RequestContactNotificationCard(
    requestContact: RequestContactDTO,
    onAcceptRequest: (UserDTO) -> Unit,
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Column(
            modifier = Modifier
                .weight(5.5f)
        ) {
            Text(text = requestContact.userRequest.username, color = MaterialTheme.colorScheme.primary)
            Text(text = "${requestContact.userRequest.firstName} ${requestContact.userRequest.lastName} ha solicitado ser tu contacto")
        }
//        Spacer(modifier = Modifier.weight(0.2f))

        IconButton(
            modifier = Modifier
                .weight(2.2f)
                .padding(start = 5.dp, top=15.dp, bottom = 15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(MaterialTheme.colorScheme.primary),
            onClick = {
                onAcceptRequest(requestContact.userRequest)
            }
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Outlined.Check,
                    contentDescription = "",
                    tint = Color.White
                )
                Text(text = "Aceptar", fontSize = 10.sp, color = Color.White)

            }
        }
    }
}

@Composable
@Preview
fun sdfsdfsdf(){
    val userDTO = UserDTO(
        username = "+346354532",
        firstName = "Juanfran",
        lastName = "Velilla",
        email = "ejemplo@correo.com"
    )
    RequestContactNotificationCard(
        requestContact = RequestContactDTO(userDTO, ""),
        onAcceptRequest = {}
    )
}

