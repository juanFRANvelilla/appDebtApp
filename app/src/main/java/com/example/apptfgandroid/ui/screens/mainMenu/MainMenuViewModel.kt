package com.example.apptfgandroid.ui.screens.mainMenu

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.useCase.UseCaseMainMenu
import com.example.apptfgandroid.useCase.UseCaseManageContact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuViewModel(
    private val useCaseManageContact: UseCaseManageContact,
    private val useCaseMainMenu: UseCaseMainMenu,

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)
    private val _request = MutableStateFlow<Set<UserDTO>>(emptySet())
    val request: StateFlow<Set<UserDTO>> = _request
//    val request: Set<UserDTO> = getUsersExample()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getRequest()
        }
    }

    fun deleteToken(){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseMainMenu.deleteToken()
        }
    }

    private fun getRequest(){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseManageContact.getRequest().collect {contacts ->
                withContext(Dispatchers.Main) {
                    _request.value = contacts
                }
            }
        }
    }

    fun acceptContactRequest(userDTOToAccept: UserDTO){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseManageContact.acceptContactRequest(userDTOToAccept.username)
            removeUser(userDTOToAccept)
        }
    }

    private fun removeUser(userToRemove: UserDTO) {
        val currentSet = _request.value
        val updatedSet = currentSet - userToRemove
        _request.value = updatedSet
    }
}