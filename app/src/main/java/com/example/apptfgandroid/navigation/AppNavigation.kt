package com.example.apptfgandroid.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.ui.screens.LoginForm
import com.example.apptfgandroid.ui.screens.MainMenu
import com.example.apptfgandroid.ui.screens.ManageContacts
import com.example.apptfgandroid.ui.screens.RegisterForm



@Composable
fun AppNavigation(appViewModel: AppViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginForm.route){
        composable(route= AppScreens.LoginForm.route){
            LoginForm(
                onNavigateRegister = { navController.navigate(AppScreens.RegisterForm.route) },
                onNavigateMainMenu = { navController.navigate(AppScreens.MainMenu.route) },
                appViewModel = appViewModel
            )
        }
        composable(route= AppScreens.RegisterForm.route){
            RegisterForm(
                onNavigateRegister = { navController.navigate(AppScreens.RegisterForm.route) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = AppScreens.MainMenu.route ) {
            MainMenu(
                onNavigateLogin = { navController.navigate(AppScreens.LoginForm.route) },
                onNavigateManageContact = { navController.navigate(AppScreens.ManageContacs.route) },
                appViewModel = appViewModel
            )
        }
        composable(route= AppScreens.ManageContacs.route){
            ManageContacts(navController, appViewModel)
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