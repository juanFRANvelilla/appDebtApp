package com.example.debtappandroid.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.debtappandroid.commonViewModel.ManageTokenViewModel
import com.example.debtappandroid.ui.screens.saveDebt.SaveDebtView
import com.example.debtappandroid.ui.screens.currentDebts.CurrentDebtsView
import com.example.debtappandroid.ui.screens.historyContactDebts.HistoryContactDebtsView
import com.example.debtappandroid.ui.screens.login.LoginForm
import com.example.debtappandroid.ui.screens.mainMenu.MainMenu
import com.example.debtappandroid.ui.screens.manageContacts.ManageContacts
import com.example.debtappandroid.ui.screens.register.RegisterForm


@Composable
fun AppNavigation(manageTokenViewModel: ManageTokenViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreens.LoginForm.route){
        composable(route= AppScreens.LoginForm.route){
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
                navController = navController
            )
        }
        composable(route= AppScreens.ManageContacs.route){
            ManageContacts(
                navController = navController
            )

        }
        composable(route= AppScreens.SaveDebt.route){
            SaveDebtView(
                navController = navController
            )

        }
        composable(route= AppScreens.CurrentDebtsView.route){
            CurrentDebtsView(
                navController = navController
            )

        }
        composable(route = AppScreens.HistoryContactDebts.route + "?username={username}",
            arguments = listOf(
                navArgument(name = "username") {
                    nullable = false
                    defaultValue = ""
                    type = NavType.StringType
                }
            )
        ) {
            HistoryContactDebtsView(
                navController,
                it.arguments?.getString("username")!!)
        }
    }
}