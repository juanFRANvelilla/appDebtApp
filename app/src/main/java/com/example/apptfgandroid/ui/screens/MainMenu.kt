package com.example.apptfgandroid.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

@Composable
fun MainMenu(
    navController: NavController,
    data: String
) {
    Box(modifier = Modifier.background(Color.Cyan)){
        Text(text = data)
    }
}

@Preview
@Composable
fun preview(){
//    MainMenu()
}
