package com.example.pets_project.repository

import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import retrofit2.Response

interface Repository {

    suspend fun login(userLoginData: UserLoginData): UserTokenResponse?
    suspend fun registration(userRegistrationData: UserRegistrationData): UserTokenResponse?

}