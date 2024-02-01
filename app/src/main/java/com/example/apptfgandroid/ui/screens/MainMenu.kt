package com.example.apptfgandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.services.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import com.example.apptfgandroid.ui.screens.ItemList as ItemList

@Composable
fun MainMenu(
    navController: NavController,
    data: String
) {
    Box(
        modifier = Modifier
            .background(Color.Cyan)
            .height(280.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),  // Center vertically within Box
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "data")
            Spacer(modifier = Modifier.height(16.dp))
            getContacts(data)
        }
    }
}


@Composable
fun getContacts(token: String){
    var response by remember { mutableStateOf<Set<UserDTO>?>(null) }
    val scope = rememberCoroutineScope()
    Button(
        onClick = {
            scope.launch {
                try {
                    val service = RetrofitService.showContacts(token)
                    response = service.showContacts()

                    println("respuesta contactos: " + response.toString())

                } catch (e: Exception) {
                    println("Error: " + e.message)
                }
            }
        }
    ) {
        Text("Contactos")
    }
    response?.let { ItemList(itemSet = it) }
}

@Composable
fun ItemList(itemSet: Set<UserDTO>) {
    LazyColumn {
        items(itemSet.toList()) { item ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = item.toString(), modifier = Modifier.weight(1f))
            }
        }
    }
}

@Preview
@Composable
fun preview(){
    val a: String = "hola"
//    MainMenu(a)
}
