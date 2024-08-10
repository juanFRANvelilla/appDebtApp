package com.example.debtappandroid.ui.screens.register

import androidx.lifecycle.ViewModel
import com.example.debtappandroid.models.user.CreateUserDTO
import com.example.debtappandroid.models.access.PhoneValidationDTO
import com.example.debtappandroid.models.access.SmsCode
import com.example.debtappandroid.useCase.UseCaseRegister
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class RegisterViewModel(
    private val useCaseRegister: UseCaseRegister
): ViewModel() {
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    fun confirmPhone(
        phoneValidationDTO: PhoneValidationDTO,
        onErrorMessageChange: (String) -> Unit,
        onIsDialogVisibleChange: (Boolean) -> Unit
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val serverResponseDTO = useCaseRegister.confirmPhone(phoneValidationDTO)
                if(serverResponseDTO.status.equals("error")){
                    onErrorMessageChange(serverResponseDTO.message)
                }
                else{
                    onIsDialogVisibleChange(true)
                }
            }
        }
    }

    fun sendSmsCode(
        smsCode: SmsCode,
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    useCaseRegister.sendSmsCode(smsCode)
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            val errorMessage = e.response()?.errorBody()?.string()
                            println(errorMessage)
                        }
                        else -> println(e.message)
                    }
                }
            }
        }
    }

    fun validatePhone(
        onCodeSmsChanged: (String) -> Unit,
        codeSms: String,
        data: CreateUserDTO,
        onIsMessageDialogVisibleChange: (Boolean) -> Unit,
        onResponseChange: (ServerResponseDTO) -> Unit,
        onAttemptsChanged: (Int) -> Unit,
        onWarningAttempts: (String) -> Unit,
        attempts: Int
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                try {
                    println("vas a hacer register")
                    val updateData = data.copy(verificationCode = codeSms)
                    val responseDTO = useCaseRegister.validatePhone(updateData)

                    onResponseChange(responseDTO)
                    onIsMessageDialogVisibleChange(true)
                }catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                401 -> {
                                    onAttemptsChanged(attempts - 1)
                                    onCodeSmsChanged("")
                                    when(attempts){
                                        0 -> {
                                            val responseDTO = ServerResponseDTO(
                                                "error",
                                                "Te has quedado sin intentos para verificar tu numero de telefono"
                                            )
                                            onResponseChange(responseDTO)
                                            onIsMessageDialogVisibleChange(true)
                                        }
                                        1 -> onWarningAttempts("Codigo incorrecto. Te queda " + attempts + " intento")
                                        else -> onWarningAttempts("Codigo incorrecto. Te quedan " + attempts + " intentos")
                                    }
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
    }
}