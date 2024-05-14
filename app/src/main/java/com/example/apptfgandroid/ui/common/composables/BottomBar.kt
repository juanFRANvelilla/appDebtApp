package com.example.apptfgandroid.ui.common.composables

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.apptfgandroid.ui.common.ItemBottomNav

@Composable
fun BottomBar(navController: NavController?) {
    val itemBottom = listOf(
        ItemBottomNav.MainMenuHome,
        ItemBottomNav.SaveDebt,
        ItemBottomNav.CurrentDebts,
        ItemBottomNav.Contacts,
    )

    BottomAppBar() {
        NavigationBar {
            itemBottom.forEach{item ->
                NavigationBarItem(
                    selected = item.title.equals("Home"),
                    onClick = {
                        navController?.navigate(item.route) {
                            popUpTo(item.route) {
                                inclusive = true
                            }
                        }
                              },
                    icon = {
                        Icon(imageVector = item.icon, contentDescription = item.title)
                    }
                )
            }
        }
    }
}