package com.example.apptfgandroid.ui.screens.MainMenu

import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.ContactRequestDTO
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
    private val useCaseMainMenu: UseCaseMainMenu

): ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Main)
    private val _request = MutableStateFlow<Set<UserDTO>>(emptySet())
    val request: StateFlow<Set<UserDTO>> = _request
//    val request: Set<UserDTO> = getUsersExample()

//    init {
//        viewModelScope.launch(Dispatchers.Main) {
//            getRequest()
//        }
//    }

    fun deleteToken(){
        viewModelScope.launch(Dispatchers.IO) {
//            useCaseMainMenu.deleteToken()
        }
    }

    fun getRequest(){
//        useCaseManageContact.getTokenFlow().collect { tokenValue ->
//            tokenValue?.let {
        viewModelScope.launch(Dispatchers.Main) {
            val token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIrMzQxIiwiaWF0IjoxNzA4MjA3Mjk3LCJleHAiOjE3MDgyOTM2OTd9.LV14Vay-sIB9vaRmgMyNgxN6Ha37TCeBGNG2Re-N6w4"
            useCaseManageContact.getRequest(token).collect {contacts ->
                withContext(Dispatchers.Main) {
                    _request.value = contacts
//                    }
//                }
                }
            }
        }


    }

    fun acceptContactRequest(userDTOToAccept: UserDTO){
//        val contactRequestDTO = ContactRequestDTO(username = userDTOToAccept.username)
//        viewModelScope.launch(Dispatchers.Main) {
//            useCaseManageContact.getTokenFlow().collect { tokenValue ->
//                tokenValue?.let {
//                    val responseDTO = useCaseManageContact.acceptContactRequest(contactRequestDTO, it)
//                    getRequest()
//                    if (responseDTO != null) {
//                    }
//                }
//            }
//        }
    }
}