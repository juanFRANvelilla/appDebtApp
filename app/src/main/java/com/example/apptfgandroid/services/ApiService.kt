package com.example.tfgapp.services

import com.example.tfgapp.models.LoginResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body data: LoginRequestDTO): LoginResponse

}



data class LoginRequestDTO(val phone:String, val password: String)

object RetrofitService{
    fun login(): ApiService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.56.1:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
