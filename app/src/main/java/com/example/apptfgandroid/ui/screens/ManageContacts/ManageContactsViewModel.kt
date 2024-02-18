package com.example.apptfgandroid.ui.screens.ManageContacts

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.ContactRequestDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ManageContactsViewModel(
    private val useCase: UseCaseManageContact,
): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Default)

    private val _contacts = MutableStateFlow<Set<UserDTO>>(emptySet())
    val contacts: StateFlow<Set<UserDTO>> = _contacts

    init {
        viewModelScope.launch {
            getContacts()
        }
    }

    fun sendContactRequest(
        request: ContactRequestDTO,
        onResponseChange: (ServerResponseDTO) -> Unit,
        onIsMessageDialogVisibleChange: (Boolean) -> Unit
    ){
//        viewModelScope.launch {
//            withContext(Dispatchers.Main) {
//                useCase.getTokenFlow().collect { tokenValue ->
//                    tokenValue?.let {
//                        val responseDTO = useCase.sendContactRequest(request, it)
//                        if (responseDTO != null) {
//                            onResponseChange(responseDTO)
//                            onIsMessageDialogVisibleChange(true)
//                        }
//                    }
//                }
//            }
//        }
    }

    private suspend fun getContacts(){
//        useCase.getTokenFlow().collect { tokenValue ->
//            tokenValue?.let {
        val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrMzQxIiwiaWF0IjoxNzA4MjA3Mjk3LCJleHAiOjE3MDgyOTM2OTd9.LV14Vay-sIB9vaRmgMyNgxN6Ha37TCeBGNG2Re-N6w4"
                useCase.getContacts(token).collect {contacts ->
                    withContext(Dispatchers.Main) {
                        _contacts.value = contacts
                    }
                }
//            }
//        }
    }
}