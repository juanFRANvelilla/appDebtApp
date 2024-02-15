package com.example.apptfgandroid.ui.screens.MainMenu

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.useCase.UseCaseManageContact
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainMenuViewModel(
    private val useCaseManageContact: UseCaseManageContact

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)
    private val _request = MutableStateFlow<Set<UserDTO>>(emptySet())
    val request: StateFlow<Set<UserDTO>> = _request

    init {
        viewModelScope.launch {
            getRequest()
        }
    }

    private suspend fun getRequest(){
        useCaseManageContact.getUsers().collect {request ->
            withContext(Dispatchers.Main){
                _request.value = request
            }
        }
    }
}