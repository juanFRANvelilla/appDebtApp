package com.example.debtappandroid.commonViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

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