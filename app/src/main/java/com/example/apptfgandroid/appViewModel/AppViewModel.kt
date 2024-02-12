package com.example.apptfgandroid.appViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.useCase.UseCaseManageContact
import com.example.apptfgandroid.useCase.preferences.GetToken
import com.example.apptfgandroid.useCase.preferences.SaveToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

class AppViewModel(
    private val useCase: UseCaseManageContact,
    private val getToken: GetToken,
    private val saveToken: SaveToken,
    private val jwToken: MutableLiveData<String>
) : ViewModel() {
    private val viewModelScope =  CoroutineScope(Dispatchers.Default)


//    fun setToken(value: String){
//        viewModelScope.launch {
//            saveToken.invoke("token",value)
//        }
//
//    }
//
//    suspend fun getToken(): String? {
//        return viewModelScope.async {
//            getToken.invoke("token")
//        }.await()
//    }

//    fun getContacts(): Set<UserDTO> {
//        return users
//    }
//
//    fun setContacts(newContacts: Set<UserDTO>) {
//        (users as MutableSet).clear()
//        (users as MutableSet).addAll(newContacts)
//    }
//
    fun getToken(): String {
        return jwToken.value.orEmpty()
    }

    fun setToken(newToken: String) {
        jwToken.value = newToken
    }
}