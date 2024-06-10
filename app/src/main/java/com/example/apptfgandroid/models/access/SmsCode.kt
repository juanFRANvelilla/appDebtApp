package com.example.apptfgandroid.models.access

import com.google.gson.annotations.SerializedName

data class SmsCode(
    @SerializedName("to")
    val to: String,
    @SerializedName("code")
    val code: String
)