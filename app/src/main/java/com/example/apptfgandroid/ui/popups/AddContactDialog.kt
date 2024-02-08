package com.example.apptfgandroid.ui.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.RequestContactDTO
import com.example.apptfgandroid.module.appModule
import com.example.apptfgandroid.navigation.AppScreens
import com.example.apptfgandroid.ui.composables.TelephoneTextField
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.services.RetrofitService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException

//@Preview
//@Composable
//fun sdfdsf(){
//    AddContactDialog {
//
//    }
//}

@Composable
fun AddContactDialog(
    onDismiss: () -> Unit,
    appViewModel: AppViewModel
) {
    var phoneNumber by remember { mutableStateOf("") }
    var countryPrefix by remember { mutableStateOf("+34") }
    var isMessageDialogVisible by remember { mutableStateOf(false) }
    var responseDTO by remember { mutableStateOf<ServerResponseDTO>(ServerResponseDTO("","")) }


    val scope = rememberCoroutineScope()


    Dialog(
        onDismissRequest = {
            onDismiss()
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(14.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(MaterialTheme.colorScheme.background)
            ) {
                TelephoneTextField(
                    phoneNumber = phoneNumber,
                    onPhoneNumberChanged = {
                        phoneNumber = it
                    },
                    onCountryPrefixChanged = {
                        countryPrefix = it
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp),
                    horizontalArrangement = Arrangement.End
                ) {
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
                            SendContactRequest(
                                scope = scope,
                                appViewModel = appViewModel,
                                request = RequestContactDTO(countryPrefix + phoneNumber),
                                onResponseChange = {
                                    responseDTO = it
                                },
                                onIsMessageDialogVisibleChange = {
                                    isMessageDialogVisible = it
                                }
                            )
                        }
                    ) {
                        Icon(imageVector = Icons.Default.Check, contentDescription = null)
                        Text("Aceptar")
                    }
                }
            }

        }

    }
    if(isMessageDialogVisible){
        ResponseMessageDialog(onDismiss = { onDismiss() }, status = responseDTO.status, message = responseDTO.message)
    }



}

fun SendContactRequest(
    scope: CoroutineScope,
    appViewModel: AppViewModel,
    request: RequestContactDTO,
    onResponseChange: (ServerResponseDTO) -> Unit,
    onIsMessageDialogVisibleChange: (Boolean) -> Unit


){
    scope.launch {
        try {
            val service = RetrofitService.contactsCallsJwt(appViewModel.getToken())
            val responseServer : Map<String, Any> = service.sendContactRequest(request)
            val responseDTO = responseServer.toServerResponseDTO()
            onResponseChange(responseDTO)
            onIsMessageDialogVisibleChange(true)

        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        400 -> {
                            val responseDTO = ConvertResponseToServerResponseDTO(
                                e.response()?.errorBody()?.string())
                            onResponseChange(responseDTO)
                            onIsMessageDialogVisibleChange(true)
                            println("error al mandar solicitud --> ${responseDTO}")
                        }
                    }
                }
//                else -> {
//                    errorMessage = "Error de servidor" + e.message
//                    println(e.message)
//                    password = ""
//                }
            }
        }
    }
}