package com.example.pets_project.repository

interface Repository {

    fun saveToken(accessToken: String, refreshToken: String)
    fun getAccessToken(): String
    fun getRefreshToken(): String
    fun isHasAuthToken(): Boolean
}
