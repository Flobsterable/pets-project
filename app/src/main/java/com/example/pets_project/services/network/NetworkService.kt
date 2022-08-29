package com.example.pets_project.services.network

import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse

interface NetworkService {

    suspend fun login(userLoginData: UserLoginData): UserTokenResponse?
    suspend fun registration(userRegistrationData: UserRegistrationData): UserTokenResponse?
    suspend fun postAd(adData: AdData): Boolean
}
