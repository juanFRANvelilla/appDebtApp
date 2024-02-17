package com.example.apptfgandroid.ui.popups

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.models.UserDTO
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import com.example.apptfgandroid.ui.screens.MainMenu.MainMenuViewModel

//@Preview
//@Composable
//fun sdfdsfsssss(){
//    ShowNotifications(onDismiss = { /*TODO*/ }, request = getUsersExample().toList())
//}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ShowNotifications(
    onDismiss: () -> Unit,
    viewModel: MainMenuViewModel,
    onAcceptRequest: (UserDTO) -> Unit,
    onRefreshPage: () -> Unit
) {
    val contactsList by rememberUpdatedState(viewModel.request.value.toList())

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
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(14.dp)
            ) {
                items(contactsList) { user ->
                    Column {
                        Text(text = user.username, color = Color.Red)
                        Text(text = "${user.firstName} ${user.lastName} ha solicitado ser tu contacto")
                        Button(
                            onClick = {
                                onAcceptRequest(user)
//                                contactsList = contactsList.filter { it != user }
//                                if(contactsList.size == 0){
//                                    onDismiss()
//                                }
                            }
                        ) {
                            Text(text = "ACEPTAR")
                        }
                    }
                }
            }
        }
    }
}
