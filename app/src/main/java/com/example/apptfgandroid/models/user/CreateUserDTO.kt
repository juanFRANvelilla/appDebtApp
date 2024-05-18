package com.example.apptfgandroid.models.user

import com.google.gson.annotations.SerializedName

data class CreateUserDTO(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("email")
    val email: String?,
    @SerializedName("firstName")
    val firstName: String,
    @SerializedName("lastName")
    val lastName: String,
    @SerializedName("verificationCode")
    val verificationCode: String
)