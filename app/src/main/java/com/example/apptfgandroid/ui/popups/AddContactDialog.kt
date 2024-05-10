package com.example.apptfgandroid.ui.popups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.apptfgandroid.ui.composables.TelephoneTextField
import com.example.apptfgandroid.ui.screens.manageContacts.ManageContactsState
import com.example.tfgapp.models.ServerResponseDTO


@Composable
fun AddContactDialog(
    onDismiss: () -> Unit,
    state: State<ManageContactsState>
) {
    var phoneNumber by remember { mutableStateOf("") }
    var countryPrefix by remember { mutableStateOf("+34") }
    var isMessageDialogVisible by remember { mutableStateOf(false) }
    var responseDTO by remember { mutableStateOf<ServerResponseDTO>(ServerResponseDTO("","")) }

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
                            val contactRequest: String = countryPrefix + phoneNumber
                            state.value.sendContactRequest(
                                contactRequest,
                                {
                                    responseDTO = it
                                },
                                {
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
