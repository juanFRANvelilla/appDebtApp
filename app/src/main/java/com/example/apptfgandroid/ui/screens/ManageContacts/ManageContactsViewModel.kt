package com.example.apptfgandroid.ui.screens.ManageContacts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.useCase.preferences.GetToken
import com.example.apptfgandroid.useCase.preferences.SaveToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
            withContext(Dispatchers.Main) {
                getUsers()
            }
        }
    }

    private suspend fun getUsers(){
//        _contacts.value = getUsersExample()
        useCase.getUsers().collect {contacts ->
            withContext(Dispatchers.Main) {
                _contacts.value = contacts
            }
        }

    }
}