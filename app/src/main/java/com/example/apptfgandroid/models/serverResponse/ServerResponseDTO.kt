package com.example.tfgapp.models

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


data class ServerResponseDTO(
    var status: String,
    var message: String
)

fun Map<String, Any>.toServerResponseDTO(): ServerResponseDTO {
    var status: String = ""
    var message: String = ""
    for ((key, value) in this) {
        status = key.toString()
        message = value.toString()
    }
    return ServerResponseDTO(status, message)
}


fun ConvertResponseToServerResponseDTO(response: String?): ServerResponseDTO{
    val type = object : TypeToken<Map<String, Any>>() {}.type
    val errorMap: Map<String, Any> = Gson().fromJson(response, type)
    return errorMap.toServerResponseDTO()
}





