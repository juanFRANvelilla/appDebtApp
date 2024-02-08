package com.example.apptfgandroid.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.module.TokenManager
import com.example.apptfgandroid.module.UserManager
import com.example.apptfgandroid.ui.screens.LoginForm
import com.example.apptfgandroid.ui.screens.MainMenu
import com.example.apptfgandroid.ui.screens.ManageContacts
import com.example.apptfgandroid.ui.screens.RegisterForm

class AppViewModel : ViewModel() {
    private val tokenManager: TokenManager = TokenManager()
    private val userManager: UserManager = UserManager()
    fun setToken(token: String) {
        tokenManager.setToken(token)
    }
    fun getToken(): String {
        return tokenManager.getToken()
    }

    fun setContacts(contacts: Set<UserDTO>){
        userManager.setUsers(contacts)
    }

    fun getContacts(): Set<UserDTO> {
        return userManager.getUsersSet()
    }
}

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    val appViewModel: AppViewModel = AppViewModel()
    NavHost(navController = navController, startDestination = AppScreens.LoginForm.route){
        composable(route= AppScreens.LoginForm.route){
            LoginForm(navController, appViewModel)
        }
        composable(route= AppScreens.RegisterForm.route){
            RegisterForm(
                onClickNavigate = { navController.navigate(AppScreens.RegisterForm.route) },
                goBackNavigation = { navController.popBackStack() }
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
        composable(
            route = AppScreens.MainMenu.route ) {
            MainMenu(navController, appViewModel)
        }
        composable(route= AppScreens.ManageContacs.route){
            ManageContacts(navController, appViewModel)
        }
    }

}