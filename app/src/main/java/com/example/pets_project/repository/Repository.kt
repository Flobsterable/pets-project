package com.example.pets_project.repository

import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.ui.screens.login.model.AuthorizationResponse

interface Repository {

    fun saveToken(accessToken: String, refreshToken: String)
    fun getAccessToken(): String
    fun getRefreshToken(): String
}