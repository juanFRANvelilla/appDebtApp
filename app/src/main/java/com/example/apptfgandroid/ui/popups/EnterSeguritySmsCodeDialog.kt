package com.example.apptfgandroid.ui.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.navigation.AppScreens
import com.example.tfgapp.services.RetrofitService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.HttpException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterSeguritySmsCodeDialog(
    onDismiss: () -> Unit,
    data: CreateUserDTO
) {
    var codeSms by remember { mutableStateOf("") }
    var attempts by remember { mutableStateOf(2) }
    var warningAttempts by remember { mutableStateOf("")}
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isMessageDialogVisible by remember { mutableStateOf(false) }
    var messageDialog by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = {
            onDismiss()
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {
            OutlinedTextField(
                value = codeSms,
                onValueChange = { codeSms = it },
                label = { Text("Ingrese el código SMS") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, start = 5.dp, end = 5.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = warningAttempts,
                    style = TextStyle(
                        color = Color.Red,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp, start = 5.dp, end = 5.dp)
                )

                Spacer(modifier = Modifier.weight(1f)) // Add a spacer to fill the available space

                TextButton(
                    onClick = {
                        onDismiss()
                    },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = null)
                    Text("Cancelar")
                }

                TextButton(
                    onClick = {
                        SendSms(
                            scope = scope,
                            codeSms = codeSms,
                            onCodeSmsChanged = {
                                codeSms = it
                            },
                            data = data,
                            onIsMessageDialogVisibleChange = { isVisible ->
                                isMessageDialogVisible = isVisible
                            },
                            onMessageDialogChanged = {
                                messageDialog = it
                            },
                            onAttemptsChanged = {
                                attempts = it
                            },
                            onWarningAttempts = {
                                warningAttempts = it
                            },
                            attempts = attempts
                        )
                    }
                ) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                    Text("Aceptar")
                }
            }
            errorMessage?.let {
                Text(
                    text = it,
                    color = Red,
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .background(Color.White)
                )
            }
            if(isMessageDialogVisible){
                ResponseMessageDialog(
                    onDismiss = {
                        onDismiss()
                    },
                    message = messageDialog
                )
            }
        }
    }
}

fun SendSms(
    scope: CoroutineScope,
    onCodeSmsChanged: (String) -> Unit,
    codeSms: String,
    data: CreateUserDTO,
    onIsMessageDialogVisibleChange: (Boolean) -> Unit,
    onMessageDialogChanged: (String) -> Unit,
    onAttemptsChanged: (Int) -> Unit,
    onWarningAttempts: (String) -> Unit,
    attempts: Int
) {
    scope.launch {
        try {
            val updateData = data.copy(verificationCode = codeSms)
            val service = RetrofitService.confirmPhone()
            val response = service.validatePhone(updateData)
            onIsMessageDialogVisibleChange(true)
            onMessageDialogChanged(response.message)

//            isMessageDialogVisible = true
//            messageDialog = response.message

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        401 -> {
                            onAttemptsChanged
                            onAttemptsChanged(attempts - 1)
                            onCodeSmsChanged("")
                            onWarningAttempts("Codigo incorrecto. Te quedan \" + attempts + \" intentos")
                        }
                        400 -> {
                            onIsMessageDialogVisibleChange(true)
                            onMessageDialogChanged("Error, te has quedado sin intentos para verificar tu numero de telefono")
//                            messageDialog = "Error, te has quedado sin intentos para verificar tu numero de telefono"
                        }
                        else -> {
                            println(e.message)
                        }
                    }
                }
                else -> {
                    println(e.message)
                }
            }
        }
    }
}

@Preview
@Composable
fun sfdsf(){
    val user = CreateUserDTO(
        username = "exampleUser",
        password = "examplePassword",
        email = "example@email.com",
        firstName = "John",
        lastName = "Doe",
        verificationCode = "123456"
    )
    EnterSeguritySmsCodeDialog(onDismiss = {
    }, data = user)
}

