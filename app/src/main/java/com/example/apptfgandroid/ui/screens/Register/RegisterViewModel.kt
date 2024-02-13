package com.example.apptfgandroid.ui.screens.Register

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.apptfgandroid.useCase.UseCaseRegister
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.RetrofitService
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
                try {
                    useCaseRegister.confirmPhone(phoneValidationDTO)
                    onIsDialogVisibleChange(true)
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                409 -> {
                                    onErrorMessageChange("El numero ya esta registrado en la base de datos")
                                }

                                else -> {
                                    onErrorMessageChange("Error de servidor")
                                }
                            }
                        }

                        else -> {
                            onErrorMessageChange("Error de servidor" + e.message)
                            println(e.message)
                        }
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
                    val updateData = data.copy(verificationCode = codeSms)
                    val responseDTO = useCaseRegister.validatePhone(updateData)
                    onResponseChange(responseDTO)
                    println("Buena respuesta --> ${responseDTO.toString()}")
                    onIsMessageDialogVisibleChange(true)
                }catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            when (e.code()) {
                                401 -> {
                                    onAttemptsChanged(attempts - 1)
                                    onCodeSmsChanged("")
                                    if(attempts == 1){
                                        onWarningAttempts("Codigo incorrecto. Te queda " + attempts + " intento")
                                    } else{
                                        onWarningAttempts("Codigo incorrecto. Te quedan " + attempts + " intentos")
                                    }

                                }
                                400 -> {
                                    val responseDTO = ServerResponseDTO(
                                        "error",
                                        "Te has quedado sin intentos para verificar tu numero de telefono"
                                    )
                                    onResponseChange(responseDTO)
                                    onIsMessageDialogVisibleChange(true)
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