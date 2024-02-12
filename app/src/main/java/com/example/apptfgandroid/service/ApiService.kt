package com.example.tfgapp.service

import com.example.apptfgandroid.R
import com.example.apptfgandroid.models.CreateUserDTO
import com.example.apptfgandroid.models.LoginRequestDTO
import com.example.apptfgandroid.models.PhoneValidationDTO
import com.example.apptfgandroid.models.RequestContactDTO
import com.example.apptfgandroid.models.UserDTO
import com.example.tfgapp.models.ServerResponseDTO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(@Body data: LoginRequestDTO): ServerResponseDTO

    @POST("confirmPhone")
    suspend fun confirmPhone(@Body data: PhoneValidationDTO): ServerResponseDTO

    @POST("validatePhone")
    suspend fun validatePhone(@Body data: CreateUserDTO): Map<String, Any>

    @GET("showContacts")
    suspend fun showContacts(): Set<UserDTO>

    @POST("requestContact")
    suspend fun sendContactRequest(@Body data: RequestContactDTO): Map<String, Any>


}



object RetrofitService{

    fun accessCalls(): ApiService{
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    fun contactsCallsJwt(token: String): ApiService{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8080/api2/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

class TokenInterceptor(private val jwtToken: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $jwtToken")
            .build()
        return chain.proceed(request)
    }
}
