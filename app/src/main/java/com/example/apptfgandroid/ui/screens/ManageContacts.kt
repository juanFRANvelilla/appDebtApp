package com.example.apptfgandroid.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.navigation.AppViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageContacts(
    navController: NavController,
    appViewModel: AppViewModel
) {
    val users: Set<UserDTO> = appViewModel.getContacts()

    Scaffold(
        content = { ManageContactsContent(users)} ,
        topBar = { ToolBarContacts(navController) }
    )
}




@Composable
fun ManageContactsContent(users: Set<UserDTO>){
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
                    imageVector = Icons.Default.Person,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarContacts(navController: NavController,){
    TopAppBar(
        title = { Text(text = "Contactos Menu") },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(AppScreens.MainMenu.route)
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
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