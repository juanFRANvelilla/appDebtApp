package com.example.apptfgandroid.ui.screens.ManageContacts

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.RequestContactDTO
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
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _contacts = MutableStateFlow<Set<UserDTO>>(emptySet())
    val contacts: StateFlow<Set<UserDTO>> = _contacts

    init {
        viewModelScope.launch {
            getContacts()
        }
    }

    fun sendContactRequest(
        request: RequestContactDTO,
        onResponseChange: (ServerResponseDTO) -> Unit,
        onIsMessageDialogVisibleChange: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                useCase.getTokenFlow().collect { tokenValue ->
                    tokenValue?.let {
                        val responseDTO = useCase.sendContactRequest(request, it)
                        if (responseDTO != null) {
                            onResponseChange(responseDTO)
                            onIsMessageDialogVisibleChange(true)
                        }
                    }
                }
            }
        }
    }

    private suspend fun getContacts(){
        useCase.getTokenFlow().collect { tokenValue ->
            tokenValue?.let {
                useCase.getContacts(it).collect {contacts ->
                    withContext(Dispatchers.Main) {
                        _contacts.value = contacts
                    }
                }
            }
        }
    }
}