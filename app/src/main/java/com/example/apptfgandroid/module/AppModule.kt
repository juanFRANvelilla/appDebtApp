package com.example.apptfgandroid.module

import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.apptfgandroid.module.Qualifier.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single(named(BaseUrl)) { "http://192.168.0.128:8080/" }
    single<Set<UserDTO>> { mutableSetOf() }
    single(named(JWToken)) { MutableLiveData<String>().apply { value = "" } }
    viewModel { AppViewModel(get(), get(named(JWToken))) }
}

enum class Qualifier{
    JWToken,
    BaseUrl,
}



class UserManager : KoinComponent {
    private val users: MutableSet<UserDTO> by inject()

    fun setUsers(contacts: Set<UserDTO>) {
        users.clear()
        users.addAll(contacts)
    }

    fun getUsersSet(): Set<UserDTO> {
        return users.toSet()
    }
}

class TokenManager : KoinComponent {
    private val jwtToken: MutableLiveData<String> by inject(named(JWToken))

    fun setToken(token: String) {
        jwtToken.value= token

    }

    fun getToken(): String {
        return jwtToken.value ?:""
    }
}

