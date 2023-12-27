package com.example.tfgapp.models

import com.google.gson.annotations.SerializedName

data class LoginResponse (
    @SerializedName("phone")
    var phone:String,
    @SerializedName("message")
    var message:String,
    @SerializedName("email")
    var email:String,
    @SerializedName("token")
    var token:String,
    @SerializedName("username")
    var username:String,
)




