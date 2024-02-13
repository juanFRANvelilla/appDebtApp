package com.example.apptfgandroid.ui.screens.Login

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
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.ui.composables.PasswordTextField
import com.example.apptfgandroid.ui.composables.TelephoneTextField
import org.koin.androidx.compose.getViewModel



@Composable
fun LoginForm(
    onNavigateRegister: () -> Unit,
    onNavigateMainMenu: () -> Unit,
) {
    val viewModel: LoginViewModel = getViewModel()

    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var countryPrefix by remember { mutableStateOf("+34") }


    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
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
                val totalPhoneNumber = countryPrefix + phoneNumber
                viewModel.doLogin(
                    loginRequest = LoginRequestDTO(username = totalPhoneNumber, password = password),
                    onNavigateMain = onNavigateMainMenu,
                    onErrorMessageChange = {
                        errorMessage = it
                    },
                    onPasswordChange = {
                        password = it
                    })
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
                      onNavigateRegister()
            },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

