package com.example.apptfgandroid.dataSource

import androidx.lifecycle.MutableLiveData
import com.example.apptfgandroid.appViewModel.AppViewModel
import com.example.apptfgandroid.models.RequestContactDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.services.RetrofitService.contactsCallsJwt
import retrofit2.HttpException

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

    suspend fun sendContactRequest(request: RequestContactDTO): ServerResponseDTO? {
        val apiService = contactsCallsJwt(appViewModel.getToken())
        try {
            val responseServer: Map<String, Any> = apiService.sendContactRequest(request)
            return responseServer.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    when (e.code()) {
                        400 -> {
                            val responseDTO = ConvertResponseToServerResponseDTO(
                                e.response()?.errorBody()?.string()
                            )
                            return responseDTO
                        }
                    }
                }
            }
        }
        return null
    }
}