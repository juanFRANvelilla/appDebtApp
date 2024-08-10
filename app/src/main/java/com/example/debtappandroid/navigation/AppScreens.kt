package com.example.debtappandroid.navigation

sealed class AppScreens(val route: String){
    object LoginForm: AppScreens("loginScreen")
    object MainMenu: AppScreens("mainMenuScreen")
    object RegisterForm: AppScreens("registerScreen")
    object SaveDebt: AppScreens("saveDebt")
    object ManageContacs: AppScreens("manageContacts")
    object CurrentDebtsView: AppScreens("currentDebtsView")
    object HistoryContactDebts: AppScreens("historyContactDebt")
}
