package com.example.apptfgandroid.ui.screens.Login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.preference.PreferenceKey
import com.example.apptfgandroid.useCase.UseCaseLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException


class LoginViewModel(
    private val useCaseLogin: UseCaseLogin,

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

//    fun saveToken(token: String){
//        viewModelScope.launch(Dispatchers.IO) {
//            useCaseLogin.saveToken(token)
//        }
//    }


//    fun getName(): String {
//        val name = ""
//        viewModelScope.launch(Dispatchers.Main) {
//            val nameValue = useCaseLogin.getNameFlow().firstOrNull()
//        }
//
//        return name
//    }


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