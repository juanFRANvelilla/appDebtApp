package com.example.tfgapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ServerResponseDTO(
    @SerializedName("message")
    var message: String
) : Parcelable {
    // Constructor secundario para manejar valores predeterminados
    constructor() : this("Default Message")
}





