package com.example.apptfgandroid.ui.screens.mainMenu

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.ui.common.composables.BottomBar
import com.example.apptfgandroid.ui.popups.ShowNotifications
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun Preview(){
    MainMenu(
        null,
    )
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    navController: NavController?
){
    val viewModel: MainMenuViewModel = getViewModel()
    val state by viewModel.state.collectAsState()

    Scaffold (
        topBar = { TopBar(navController, state) },
        content = { MainMenuContent(navController) },
        bottomBar = { BottomBar(navController) }
    )
}




@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    navController: NavController?,
    state: MainMenuState
){
    val requestList = state.contactRequest
    var expandedRequest by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(text = "Debt App") },
        navigationIcon = {
            IconButton(onClick = {
                state.deleteToken()
                navController?.navigate(AppScreens.LoginForm.route) {
                    popUpTo(AppScreens.LoginForm.route) {
                        inclusive = true
                    }
                }
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
            }
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenuContent(
    navController: NavController?
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        PieChart(
            data = mapOf(
                Pair("Te deben", 150),
                Pair("Debes", 120),
            )
        )

    }
}

