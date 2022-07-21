package com.example.pets_project.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.pets_project.R
import com.example.pets_project.services.network.NetworkService
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.ui.screens.login.model.AuthorizationResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val appContext: Context,
) : Repository {

    private val KEY_SHARED_PREFERENCE = appContext.getString(R.string.SHARED_PREFERENCE)
    private val KEY_ACCESS_TOKEN = appContext.getString(R.string.ACCESS_TOKEN)
    private val KEY_REFRESH_TOKEN = appContext.getString(R.string.REFRESH_TOKEN)

    private val preferences : SharedPreferences = appContext.getSharedPreferences(
        KEY_SHARED_PREFERENCE,
        Context.MODE_PRIVATE)

    override fun saveToken(accessToken: String, refreshToken: String) {
        preferences.edit().putString(KEY_ACCESS_TOKEN,accessToken).apply()
        preferences.edit().putString(KEY_REFRESH_TOKEN, refreshToken).apply()
    }

    override fun getAccessToken(): String {
        return preferences.getString(KEY_ACCESS_TOKEN, "")!!
    }

    override fun getRefreshToken(): String {
        return preferences.getString(KEY_REFRESH_TOKEN, "")!!
    }


}