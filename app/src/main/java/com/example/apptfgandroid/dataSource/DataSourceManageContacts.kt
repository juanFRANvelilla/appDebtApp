package com.example.apptfgandroid.dataSource

import com.example.apptfgandroid.models.ContactRequestDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ConvertResponseToServerResponseDTO
import com.example.tfgapp.models.ServerResponseDTO
import com.example.tfgapp.models.toServerResponseDTO
import com.example.tfgapp.service.RetrofitService.contactsCallsJwt
import retrofit2.HttpException

class DataSourceManageContacts() {
    suspend fun getContacts(token: String): Set<UserDTO> {
        val apiService = contactsCallsJwt(token)
        return try {
            apiService.showContacts()
        } catch (e: Exception) {
            emptySet()
        }
    }

    suspend fun getRequest(token: String): Set<UserDTO> {
        val apiService = contactsCallsJwt(token)
        return try {
            apiService.showContactRequest()
        } catch (e: Exception) {
            emptySet()
        }
    }

    suspend fun sendContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? {
        val apiService = contactsCallsJwt(token)
        try {
            val responseServer: Map<String, Any> = apiService.sendContactRequest(request)
            return responseServer.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Respuesta de error $errorMessage")
                    if(!errorMessage.equals("")){
                        val responseDTO = ConvertResponseToServerResponseDTO(errorMessage)
                        return responseDTO
                    }
                    else{
                        return null
                    }

                }
                else -> println(e.message)
            }
        }
        return null
    }

    suspend fun acceptContactRequest(request: ContactRequestDTO, token: String): ServerResponseDTO? {
        val apiService = contactsCallsJwt(token)
        try {
            val responseServer: Map<String, Any> = apiService.acceptContactRequest(request)
            return responseServer.toServerResponseDTO()
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    val errorMessage = e.response()?.errorBody()?.string()
                    println("Respuesta de error $errorMessage")
                    if(!errorMessage.equals("")){
                        val responseDTO = ConvertResponseToServerResponseDTO(errorMessage)
                        return responseDTO
                    }
                    else{
                        return null
                    }
                }
                else -> println(e.message)
            }
        }
        return null
    }

}