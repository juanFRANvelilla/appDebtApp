package com.example.apptfgandroid.ui.screens.manageContacts

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.ui.popups.AddContactDialog
import org.koin.androidx.compose.getViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageContacts(
    onNavigateMainMenu: () -> Unit
) {
    val viewModel: ManageContactsViewModel = getViewModel()
    val state = viewModel.state.collectAsState()
    Scaffold(
        content = { ManageContactsContent(state) } ,
        topBar = { ToolBarContacts(onNavigateMainMenu, state) }
    )
}


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ManageContactsContent(state: State<ManageContactsState>){
    val contactsList = state.value.contacts.toList()
    LazyColumn(
        modifier = Modifier
            .background(Color.White)
            .padding(top = 70.dp)
    ) {
        items(contactsList) { user ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 16.dp)
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
                    if (user.email.isNotEmpty() == true) {
                        Text(text = "Email: ${user.email}")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBarContacts(onNavigateMainMenu: () -> Unit, state: State<ManageContactsState>) {
    TopAppBar(
        title = { Text(text = "Contactos Menu") },
        navigationIcon = {
            IconButton(onClick = {
                onNavigateMainMenu()
            }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            AddUsers(state)
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@Composable
fun AddUsers(state: State<ManageContactsState>){
    var addDialog by remember { mutableStateOf(false) }
    IconButton(onClick = {
        addDialog = true
    }){
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 12.dp)
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Contact",
            tint = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .size(26.dp)
                .padding(bottom = 10.dp, start = 12.dp)
        )
    }
    if (addDialog){
        AddContactDialog(onDismiss = { addDialog = false }, state = state)
    }
}


fun getUsersExample(): Set<UserDTO> {
    val users = mutableSetOf<UserDTO>()
    for (i in 0 until 5) {
        val user = UserDTO(
            firstName = "First Name $i",
            lastName = "Last Name $i",
            username = "Username$i",
            email = if (i%3==0) "ejemplo@mail.com" else ""
        )
        users.add(user)
    }
    return users
}