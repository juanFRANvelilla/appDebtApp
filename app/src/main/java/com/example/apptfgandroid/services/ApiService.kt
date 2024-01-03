package com.example.tfgapp.services

import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.tfgapp.models.ServerResponseDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body data: LoginRequestDTO): ServerResponseDTO

    @POST("confirmPhone")
    suspend fun confirmPhone(@Body data: PhoneValidationDTO): ServerResponseDTO

    @POST("validatePhone")
    suspend fun validatePhone(@Body data: CreateUserDTO): ServerResponseDTO


}





object RetrofitService{
    fun login(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun confirmPhone(): ApiService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun validatePhone(): ApiService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
