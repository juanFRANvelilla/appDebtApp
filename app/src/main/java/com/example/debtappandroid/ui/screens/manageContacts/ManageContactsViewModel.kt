package com.example.debtappandroid.ui.screens.manageContacts

import androidx.lifecycle.ViewModel
import com.example.debtappandroid.useCase.UseCaseManageContact
import com.example.debtappandroid.utils.toCommonMutableStateFlow
import com.example.tfgapp.models.ServerResponseDTO
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ManageContactsViewModel(
    private val useCase: UseCaseManageContact
): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Default)

    private val _state = MutableStateFlow(ManageContactsState())
    val state = _state.toCommonMutableStateFlow()


    init {
        viewModelScope.launch {
            getContacts()
        }
        _state.value.sendContactRequest = {contactRequest, onResponseChange, onIsMessageDialogVisibleChange -> sendContactRequest(contactRequest, onResponseChange, onIsMessageDialogVisibleChange)}
    }

    private fun sendContactRequest(
        contactRequest: String,
        onResponseChange: (ServerResponseDTO) -> Unit,
        onIsMessageDialogVisibleChange: (Boolean) -> Unit
    ){
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val responseDTO = useCase.sendContactRequest(contactRequest)
                if (responseDTO != null) {
                    onResponseChange(responseDTO)
                    onIsMessageDialogVisibleChange(true)
                }
            }
        }
    }

    private suspend fun getContacts(){
        useCase.getContacts().collect {contacts ->
            withContext(Dispatchers.Main) {
                _state.update {
                    it.copy(
                        contacts = contacts
                    )
                }
            }
        }
    }
}