package com.example.apptfgandroid.navigation

sealed class AppScreens(val route: String){
    object LoginForm: AppScreens("loginScreen")
    object MainMenu: AppScreens("mainMenuScreen")
    object RegisterForm: AppScreens("registerScreen")
}
