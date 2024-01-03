package com.example.apptfgandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apptfgandroid.ui.screens.LoginForm
import com.example.apptfgandroid.ui.screens.MainMenu
import com.example.apptfgandroid.ui.screens.RegisterForm


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginForm.route){
        composable(route= AppScreens.LoginForm.route){
            LoginForm(navController)
        }
        composable(route= AppScreens.RegisterForm.route){
            RegisterForm(navController)
        }
        composable(
            route = AppScreens.MainMenu.route + "/{data}",
            arguments = listOf(
                navArgument(name = "data") {
                    type = NavType.StringType
                }
            )
        ) {
            MainMenu(navController, it.arguments?.getString("data").toString())
        }
    }

}