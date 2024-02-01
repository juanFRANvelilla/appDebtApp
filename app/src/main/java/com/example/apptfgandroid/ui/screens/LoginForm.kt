package com.example.apptfgandroid.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.ui.composables.PasswordTextField
import com.example.apptfgandroid.ui.composables.TelephoneTextField
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.services.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.HttpException


@Composable
fun LoginForm(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var countryPrefix by remember { mutableStateOf("+34") }

    val scope = rememberCoroutineScope()

    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {
        Text(
            text = "Acceso",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge.copy()
        )
        TelephoneTextField(
            phoneNumber = phoneNumber,
            onPhoneNumberChanged = {
                phoneNumber = it
            },
            onCountryPrefixChanged = {
                countryPrefix = it
            }
        )
        PasswordTextField(
            password = password,
            onPasswordChanged = { password = it },
            title = "Contraseña"
        )

        errorMessage?.let {
            Text(
                text = it,
                color = Color.Red,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Button(
            onClick = {
                scope.launch {
                    try {
                        val service = RetrofitService.login()
                        val totalPhoneNumber = countryPrefix + phoneNumber
                        val response: ServerResponseDTO = service.login(LoginRequestDTO(username = totalPhoneNumber, password = password))
                        errorMessage = null
                        navController.navigate(route = AppScreens.MainMenu.route + "/" + response.message)
                    } catch (e: Exception) {
                        when (e) {
                            is HttpException -> {
                                when (e.code()) {
                                    403 -> {
                                        errorMessage = "Credenciales incorrectas"
                                        password = ""
                                    }
                                    else -> {
                                        errorMessage = "Error de servidor"
                                        password = ""
                                    }
                                }
                            }
                            else -> {
                                errorMessage = "Error de servidor" + e.message
                                println(e.message)
                                password = ""
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .width(180.dp)
                .align(Alignment.CenterHorizontally)
                .padding(top = 16.dp),
            enabled = phoneNumber.isNotEmpty() &&
                    countryPrefix.isNotEmpty() &&
                    password.isNotEmpty()
        ) {
            Text("Login")
        }
        ClickableText(
            text = AnnotatedString("Aun no estas registrado?, click aqui"),
            onClick = {
                navController.navigate(route = AppScreens.RegisterForm.route)
            },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}