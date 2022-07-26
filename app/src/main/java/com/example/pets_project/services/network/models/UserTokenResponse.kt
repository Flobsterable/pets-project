package com.example.pets_project.services.network.models

data class UserTokenResponse(
    val accessToken: String,
    val refreshToken: String
)

