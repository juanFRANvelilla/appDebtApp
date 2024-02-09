package com.example.apptfgandroid.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.apptfgandroid.ui.composables.PasswordTextField
import com.example.apptfgandroid.ui.composables.TelephoneTextField
import com.example.apptfgandroid.ui.popups.EnterSeguritySmsCodeDialog
import com.example.tfgapp.services.RetrofitService
import kotlinx.coroutines.launch
import retrofit2.HttpException
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterForm(
    onNavigateRegister: () -> Unit,
    onNavigateBack: () -> Unit
) {
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var countryPrefix by remember { mutableStateOf("+34") }
    var verificationCode by remember { mutableStateOf("") }
    var isDialogVisible by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .padding(top = 120.dp)
    ) {
        Text(
            text = "Registrar Nuevo Usuario",
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge.copy()
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = firstName,
                onValueChange = { firstName = it },
                label = { Text("Nombre*") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp)
            )

            OutlinedTextField(
                value = lastName,
                onValueChange = { lastName = it },
                label = { Text("Apellido*") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp)
            )
        }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
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
            title = "Contraseña*"
        )
        PasswordTextField(
            password = confirmPassword,
            onPasswordChanged = { confirmPassword = it },
            title = "Confirmar Contaseña*"
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
                        errorMessage = null
                        if(!password.equals(confirmPassword)){
                            errorMessage = "La contraseña no coincide"
                        }
                        else{
                            val service = RetrofitService.accessCalls()
                            val randomNumber = Random.nextInt(0, 1000000)
                            verificationCode = String.format("%06d", randomNumber)
                            val phoneValidationDTO = PhoneValidationDTO(
                                username = countryPrefix + phoneNumber,
                                verificationCode = verificationCode
                            )

                            val response = service.confirmPhone(phoneValidationDTO)

                            println("Respuestaa 1: " + response)
                            isDialogVisible = true;
                        }
                    } catch (e: Exception) {
                        when (e) {
                            is HttpException -> {
                                when (e.code()) {
                                    409 -> {
                                        errorMessage = "El numero ya esta registrado en la base de datos"
                                    }
                                    else -> {
                                        errorMessage = "Error de servidor"
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
            enabled = firstName.isNotEmpty() &&
                    lastName.isNotEmpty() &&
                    password.isNotEmpty() &&
                    confirmPassword.isNotEmpty() &&
                    phoneNumber.isNotEmpty() &&
                    countryPrefix.isNotEmpty()
        ) {
            Text("Registrar usuario")
        }

        if(isDialogVisible){
            val createUserDTO = CreateUserDTO(
                username = countryPrefix + phoneNumber,
                password = password,
                email = email,
                firstName = firstName,
                lastName = lastName,
                verificationCode = ""
            )
            EnterSeguritySmsCodeDialog(
                onDismiss = {
                    isDialogVisible = false
                    onNavigateRegister()
                },
                data = createUserDTO
            )
        }
        GoBack(onNavigateBack)
    }
}


@Composable
fun GoBack(onNavigateBack: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextButton(
            onClick = {
                onNavigateBack()
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text("Volver al login")
        }
    }
}




//@Preview
//@Composable
//fun Preview(){
////    RegisterForm()
//    GoBack(navController = rememberNavController())
//}


