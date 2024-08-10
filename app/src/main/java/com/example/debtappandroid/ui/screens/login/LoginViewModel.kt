package com.example.debtappandroid.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.debtappandroid.models.access.LoginRequestDTO
import com.example.debtappandroid.useCase.UseCaseLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class LoginViewModel(
    private val useCaseLogin: UseCaseLogin,

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    fun doLogin(
        loginRequest: LoginRequestDTO,
        onNavigateMain: () -> Unit,
        onErrorMessageChange: (String?) -> Unit,
        onPasswordChange: (String) -> Unit
    ){
        viewModelScope.launch {
            withContext(Dispatchers.Main){
                try{
                    useCaseLogin.doLogin(loginRequest)
                    onNavigateMain()
                }
                catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                403 -> {
                                    onErrorMessageChange("Credenciales incorrectas")
                                    onPasswordChange("")
                                }
                            }
                        }
                        else -> {
                            onErrorMessageChange("Error de servidor")
                            println(e.message)
                        }
                    }
                }
            }
        }
    }
}