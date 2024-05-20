package com.example.tfgapp.service

import com.example.apptfgandroid.models.balance.BalanceDTO
import com.example.apptfgandroid.models.debt.CreateDebtDTO
import com.example.apptfgandroid.models.user.CreateUserDTO
import com.example.apptfgandroid.models.debt.DebtDTO
import com.example.apptfgandroid.models.access.LoginRequestDTO
import com.example.apptfgandroid.models.access.PhoneValidationDTO
import com.example.apptfgandroid.models.notification.DebtNotificationDTO
import com.example.apptfgandroid.models.notification.NotificationDTO
import com.example.apptfgandroid.models.user.UserDTO
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("login")
    suspend fun login(@Body data: LoginRequestDTO): Map<String, Any>

    @POST("api/confirmPhone")
    suspend fun confirmPhone(@Body data: PhoneValidationDTO): Map<String, Any>

    @POST("api/validatePhone")
    suspend fun validatePhone(@Body data: CreateUserDTO): Map<String, Any>

    @GET("api2/showContacts")
    suspend fun showContacts(): Set<UserDTO>

    @GET("api2/requestContact")
    suspend fun sendContactRequest(@Query("contactUsername") contactUsername: String): Map<String, Any>

    @GET("api2/acceptRequestContact")
    suspend fun acceptContactRequest(@Query("contactUsername") contactUsername: String): Map<String, Any>

//    @GET("api2/showRequestContact")
//    suspend fun showContactRequest(): Set<UserDTO>

    @GET("notification/getNotifications")
    suspend fun getNotifications(): NotificationDTO

    @POST("notification/deleteDebtNotification")
    suspend fun removeNotification(@Body data: DebtNotificationDTO): Map<String, Any>

    @POST("notification/createDebtNotification")
    suspend fun createDebtNotification(@Body data: DebtNotificationDTO): Map<String, Any>

    @POST("debt/saveDebt")
    suspend fun saveDebt(@Body data: CreateDebtDTO): Map<String, Any>

    @GET("debt/getBalance")
    suspend fun getBalance(): BalanceDTO

    @GET("debt/getCurrentDebts")
    suspend fun getCurrentDebts(): List<DebtDTO>

    @GET("debt/payOffDebt")
    suspend fun payOffDebt(@Query("debtId") debtId: Int): Map<String, Any>
}



object RetrofitService{
    fun contactsCallsJwt(token: String): ApiService{
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor(token))
            .build()

        return Retrofit.Builder()
            .baseUrl("http://192.168.0.128:8080/")
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
