package com.example.apptfgandroid.ui.screens.Login

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.preference.PreferenceKey
import com.example.apptfgandroid.useCase.UseCaseLogin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.firstOrNull


class LoginViewModel(
    private val useCaseLogin: UseCaseLogin,
    private val context: Context
): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

//    val nameFlow: Flow<String?> = context.datastore.data.map { preferences ->
//        preferences[PreferenceKey.NAME]
//    }

    val darkModeFlow: Flow<Boolean?> = context.datastore.data.map { darkMode ->
        darkMode[PreferenceKey.DARKMODE]
    }

    suspend fun getDarkModeValue(): Boolean {
        return context.datastore.data.map { darkmode ->
            darkmode[PreferenceKey.DARKMODE] ?: false
        }.firstOrNull() ?: false
    }

    fun saveName(name: String){
        viewModelScope.launch(Dispatchers.IO) {
            context.datastore.edit { preferences ->
                preferences[PreferenceKey.NAME] = name
            }
        }
    }

    fun changeDarkMode(value: Boolean){
        viewModelScope.launch(Dispatchers.IO) {
            context.datastore.edit { darkMode ->
                darkMode[PreferenceKey.DARKMODE] = value
            }
        }
    }

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