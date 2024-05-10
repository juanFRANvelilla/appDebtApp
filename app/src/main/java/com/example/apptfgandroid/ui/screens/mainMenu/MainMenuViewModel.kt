package com.example.apptfgandroid.ui.screens.mainMenu

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.toCommonMutableStateFlow
import com.example.apptfgandroid.useCase.UseCaseMainMenu
import com.example.apptfgandroid.useCase.UseCaseManageContact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuViewModel(
    private val useCaseManageContact: UseCaseManageContact,
    private val useCaseMainMenu: UseCaseMainMenu,

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)

    private val _state = MutableStateFlow(MainMenuState())
    val state = _state.toCommonMutableStateFlow()

    init {
        viewModelScope.launch(Dispatchers.Main) {
            getRequest()
            getBalance()
        }
        _state.value.deleteToken = { deleteToken() }
        _state.value.acceptContactRequest = { userDTO -> acceptContactRequest(userDTO) }
    }

    private fun deleteToken(){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseMainMenu.deleteToken()
        }
    }

    private fun getBalance(){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseMainMenu.getBalance().collect {balance ->
                withContext(Dispatchers.Main) {
                    if(balance != null){
                        _state.update {
                            it.copy(
                                balance = balance
                            )
                        }
                    }
                }
            }
        }
    }

    private fun getRequest(){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseManageContact.getRequest().collect {request ->
                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            contactRequest = request
                        )
                    }
                }
            }
        }
    }

    private fun acceptContactRequest(userDTOToAccept: UserDTO){
        viewModelScope.launch(Dispatchers.Main) {
            useCaseManageContact.acceptContactRequest(userDTOToAccept.username)
            removeUser(userDTOToAccept)
        }
    }

    private fun removeUser(userToRemove: UserDTO) {
        val currentSet = _state.value.contactRequest
        val updatedSet = currentSet - userToRemove
        _state.update {
            it.copy(
                contactRequest = updatedSet
            )
        }
    }
}