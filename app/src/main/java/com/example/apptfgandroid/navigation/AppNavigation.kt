package com.example.apptfgandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.ui.screens.Login.LoginForm
import com.example.apptfgandroid.ui.screens.MainMenu.MainMenu
import com.example.apptfgandroid.ui.screens.ManageContacts.ManageContacts
import com.example.apptfgandroid.ui.screens.Register.RegisterForm



@Composable
fun AppNavigation(appViewModel: AppViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginForm.route){
        composable(route= AppScreens.LoginForm.route){
//            MainMenu(
//                onNavigateLogin = { navController.navigate(AppScreens.LoginForm.route) },
//                onNavigateManageContact = { navController.navigate(AppScreens.ManageContacs.route) },
//                onRefreshPage = { navController.navigate(AppScreens.MainMenu.route) }
//            )
            LoginForm(
                onNavigateRegister = { navController.navigate(AppScreens.RegisterForm.route) },
                onNavigateMainMenu = { navController.navigate(AppScreens.MainMenu.route) }
            )
        }
        composable(route= AppScreens.RegisterForm.route){
            RegisterForm(
                onNavigateRegister = { navController.navigate(AppScreens.RegisterForm.route) },
                onNavigateLogin = { navController.navigate(AppScreens.LoginForm.route) }
            )
        }
        composable(
            route = AppScreens.MainMenu.route ) {
            MainMenu(
                onNavigateLogin = { navController.navigate(AppScreens.LoginForm.route) },
                onNavigateManageContact = { navController.navigate(AppScreens.ManageContacs.route) },
                onRefreshPage = { navController.navigate(AppScreens.MainMenu.route) }
            )
        }
        composable(route= AppScreens.ManageContacs.route){
            ManageContacts(
                onNavigateMainMenu = { navController.navigate(AppScreens.MainMenu.route) }
            )
        }
//        composable(
//            route = AppScreens.MainMenu.route + "/{data}",
//            arguments = listOf(
//                navArgument(name = "data") {
//                    type = NavType.StringType
//                }
//            )
//        ) {
//            MainMenu(navController, it.arguments?.getString("data").toString())
//        }
    }

}