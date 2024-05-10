package com.example.apptfgandroid.ui.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.ui.popups.ShowNotifications
import org.koin.androidx.compose.getViewModel

//@Preview
//@Composable
//fun Preview(){
//    MainMenu(
//        onNavigateLogin = {},
//        onNavigateManageContact = {}
//    )
//}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    onNavigateLogin: () -> Unit,
    onNavigateManageContact: () -> Unit,
    onRefreshPage: () -> Unit
){
    val viewModel: MainMenuViewModel = getViewModel()
    val state by viewModel.state.collectAsState()
    Scaffold (
        topBar = { ToolBar(onNavigateLogin, state, onRefreshPage ) },
        content = { MainMenuContent(onNavigateManageContact) }
    )
}


@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    onNavigateLogin: () -> Unit,
    state: MainMenuState,
    onRefreshPage: () -> Unit
){
//    val requestList by rememberUpdatedState(viewModel.request.value.toList())
    val requestList = state.contactRequest
    var expandedRequest by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "Main Menu") },
        navigationIcon = {
            IconButton(onClick = {
                state.deleteToken()
                onNavigateLogin()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
        actions = {
            IconButton(
                onClick = {
                    expandedRequest = !expandedRequest
                },
                modifier = Modifier
                    .padding(end = 10.dp)
                    .width(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .padding(end = 18.dp),
                )
                val numberOfRequest = requestList.size
                Text(
                    text = if (numberOfRequest < 10) numberOfRequest.toString() else " 9+",
                    modifier = Modifier
                        .padding(start = 14.dp, bottom = 14.dp),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
    if(expandedRequest && state.contactRequest.isNotEmpty()){
        ShowNotifications(
            onDismiss = {
                expandedRequest = false
            },
            requestList = requestList.toMutableList(),
            onAcceptRequest = {
                state.acceptContactRequest(it)
            },
            onRefreshPage = {
                onRefreshPage()
            }
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenuContent(
    onNavigateManageContact: () -> Unit,
) {
    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .height(280.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    onNavigateManageContact()
                }
            ) {
                Text("Contactos")
            }
        }
    }
}






