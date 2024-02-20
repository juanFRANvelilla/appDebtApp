package com.example.apptfgandroid.commonViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class ManageTokenViewModel(
    private val jwToken: MutableLiveData<String>
) : ViewModel() {

    fun getToken(): String {
        return jwToken.value.orEmpty()
    }

    fun setToken(newToken: String) {
        jwToken.value = newToken
    }
}