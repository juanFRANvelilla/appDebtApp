package com.example.apptfgandroid.models

import com.google.gson.annotations.SerializedName

data class PhoneValidationDTO(
    @SerializedName("username")
    val username: String,
    @SerializedName("verificationCode")
    val verificationCode: String
)