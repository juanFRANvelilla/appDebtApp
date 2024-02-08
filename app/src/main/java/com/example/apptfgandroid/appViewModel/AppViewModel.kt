package com.example.apptfgandroid.appViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apptfgandroid.models.UserDTO

//class AppViewModel() : ViewModel() {
//    private val tokenManager: TokenManager = TokenManager()
//    private val userManager: UserManager = UserManager()
//    fun setToken(token: String) {
//        tokenManager.setToken(token)
//    }
//    fun getToken(): String {
//        return tokenManager.getToken()
//    }
//
//    fun setContacts(contacts: Set<UserDTO>){
//        userManager.setUsers(contacts)
//    }
//
//    fun getContacts(): Set<UserDTO> {
//        return userManager.getUsersSet()
//    }
//}

class AppViewModel(
    private val users: Set<UserDTO>,
    private val jwToken: MutableLiveData<String>
) : ViewModel() {
    fun getContacts(): Set<UserDTO> {
        return users
    }

    fun setContacts(newContacts: Set<UserDTO>) {
        (users as MutableSet).clear()
        (users as MutableSet).addAll(newContacts)
    }

    fun getToken(): String {
        return jwToken.value.orEmpty()
    }

    fun setToken(newToken: String) {
        jwToken.value = newToken
    }
}