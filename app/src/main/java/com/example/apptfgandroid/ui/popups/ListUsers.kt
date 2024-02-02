package com.example.apptfgandroid.ui.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.R
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.UserDTO

@Composable
fun ListUsers(
    onDismiss: () -> Unit,
    users: Set<UserDTO>
//    users: MutableSet<UserDTO> = getUsers()
) {
    Dialog(
        onDismissRequest = {
            onDismiss()
        },
    ){
        LazyColumn(
            modifier = Modifier
                .background(Color.White)
        ) {
            items(users.toList()) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                            .padding(12.dp)
                            .align(Alignment.CenterVertically)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Column {
                        Text(text = "Number: ${user.username}")
                        Text(text = "Name: ${user.firstName}", fontWeight = FontWeight.Bold)
                        Text(text = "Last Name: ${user.lastName}")
                        if (user.email?.isNotEmpty() == true) {
                            Text(text = "Email: ${user.email}")
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun preview(){
    var users = getUsers()
    ListUsers(
        onDismiss = {},
        users = users,)
}

fun getUsers(): MutableSet<UserDTO> {
    val users = mutableSetOf<UserDTO>()
    for (i in 0 until 10) {
        val user = UserDTO(
            firstName = "First Name $i",
            lastName = "Last Name $i",
            username = "Username$i",
            email = ""
        )
        users.add(user)
    }
    return users
}