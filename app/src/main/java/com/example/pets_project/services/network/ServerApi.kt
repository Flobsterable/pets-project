package com.example.pets_project.services.network

import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServerApi {

    @GET("tech/healthcheck")
    suspend fun healthCheck(): Response<Void>

    @POST("login/email")
    suspend fun login (@Body userLoginData: UserLoginData): Response<UserTokenResponse>

    @POST("register/email")
    suspend fun registration(@Body userRegistrationData: UserRegistrationData): Response<UserTokenResponse>



}