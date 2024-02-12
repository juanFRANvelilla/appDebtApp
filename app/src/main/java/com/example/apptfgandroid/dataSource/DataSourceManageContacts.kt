package com.example.apptfgandroid.dataSource

import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.services.RetrofitService.contactsCallsJwt

class DataSourceManageContacts(
    val appViewModel: AppViewModel
) {
    suspend fun getUsers(): Set<UserDTO> {
        val apiService = contactsCallsJwt(appViewModel.getToken())
        return try {
            apiService.showContacts()
        } catch (e: Exception) {
            emptySet()
        }
    }
}