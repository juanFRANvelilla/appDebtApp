package com.example.apptfgandroid.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.apptfgandroid.navigation.AppScreens

sealed class ItemBottomNav(
    val icon: ImageVector,
    val title: String,
    val route: String
) {
    object MainMenuHome: ItemBottomNav(
        icon = Icons.Outlined.Home,
        title = "Home",
        route = AppScreens.MainMenu.route
    )

    object SaveDebt: ItemBottomNav(
        icon = Icons.Outlined.Add,
        title = "Guardar Deuda",
        route = AppScreens.SaveDebt.route
    )

    object CurrentDebts: ItemBottomNav(
        icon = Icons.Outlined.List,
        title = "Deudas Activas",
        route = AppScreens.CurrentDebtsView.route
    )

    object Contacts: ItemBottomNav(
        icon = Icons.Outlined.AccountBox,
        title = "Contactos",
        route = AppScreens.ManageContacs.route
    )

}