package com.example.pets_project.services.network

import android.util.Log
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit



class NetworkServiceImpl : NetworkService
{

    private fun getBaseUr(): String = "https://petsproject.issart.com/api/1.0.0/"
    private val gson = GsonConverterFactory.create(GsonBuilder().create())

    private val client = OkHttpClient.Builder()
        .callTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .build()

    private val retrofit = Retrofit
        .Builder()
        .client(client)
        .baseUrl(getBaseUr())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(gson)
        .build()

    private val networkService = retrofit.create(ServerApi::class.java)


    override suspend fun login(userLoginData: UserLoginData): UserTokenResponse? {

      val healthCheckResponse = networkService.healthCheck()
        when(healthCheckResponse.code()) {
            200 -> Log.e("network","OK")
            else -> Log.e("network. error", "${healthCheckResponse.code()}")
        }

        val tokenResponse = networkService.login(userLoginData)

        return when(tokenResponse.code()) {
            200 -> {
                Log.e("network","Login: OK")
                tokenResponse.body()
            }
            else -> {
                Log.e("ErrorCode Login:", "${tokenResponse.code()}")
                null
            }
        }
        

    }

    override suspend fun registration(userRegistrationData: UserRegistrationData): UserTokenResponse? {

        val healthCheckResponse = networkService.healthCheck()
        when(healthCheckResponse.code()) {
            200 -> Log.e("network","OK")
            else -> Log.e("network. error", "${healthCheckResponse.code()}")
        }

        val tokenResponse = networkService.registration(userRegistrationData)
        return when(tokenResponse.code()) {
            200 -> {
                Log.e("network","Registration: OK")
                tokenResponse.body()
            }
            else -> {
                Log.e("ErrorCode Registration:", "${tokenResponse.code()}")
                null
            }
        }
    }

}