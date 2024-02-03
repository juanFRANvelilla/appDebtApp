package com.example.apptfgandroid.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.navigation.AppViewModel
import com.example.tfgapp.services.RetrofitService
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(navController: NavController,){
    TopAppBar(
        title = { Text(text = "Main Menu") },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(AppScreens.LoginForm.route)
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainMenu(
    navController: NavController,
    appViewModel: AppViewModel
){
    val data = appViewModel.getToken()
    Scaffold (
        topBar = { ToolBar(navController) },
        content = { MainMenuContent(navController, appViewModel) }
    )
}



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainMenuContent(
    navController: NavController,
    appViewModel: AppViewModel,
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
            Text(text = "data")
            Spacer(modifier = Modifier.height(16.dp))
            getContacts(appViewModel.getToken(), navController, appViewModel)
        }
    }
}




@Composable
fun getContacts(token: String, navController: NavController,appViewModel: AppViewModel){
    var response by remember { mutableStateOf<Set<UserDTO>?>(null) }
    var openListUserDialog by remember { mutableStateOf<Boolean>(false) }
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                try {
                    val service = RetrofitService.showContacts(token)
                    response = service.showContacts()
                    if (response?.size ?: 0 > 0) {
                        openListUserDialog = true
                        appViewModel.setContacts(response!!)
                        navController.navigate(AppScreens.ManageContacs.route)
                    }

                    println("respuesta contactos: " + response.toString())

                } catch (e: Exception) {
                    println("Error: " + e.message)
                }
            }
        }
    ) {
        Text("Contactos")
    }
}


//@Preview
//@Composable
//fun preview(){
//    val a: String = "hola"
//    val navController = rememberNavController()
//    MainMenu(navController,a)
////    ToolBar()
//}
