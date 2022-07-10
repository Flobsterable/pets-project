package com.example.pets_project.repository

import android.util.Log
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.NetworkServiceImpl
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import retrofit2.Response
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkService: NetworkService
    ) : Repository {



    override suspend fun login(userLoginData: UserLoginData): UserTokenResponse?
    {
        return networkService.login(userLoginData)
    }

    override suspend fun registration(userRegistrationData: UserRegistrationData): UserTokenResponse? {
        return null
    }

}