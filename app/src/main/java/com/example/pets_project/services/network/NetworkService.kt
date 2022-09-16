package com.example.pets_project.services.network

import android.content.Context
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.viewModels.AdViewModel

interface NetworkService {

    suspend fun login(userLoginData: UserLoginData): UserTokenResponse?
    suspend fun registration(userRegistrationData: UserRegistrationData): UserTokenResponse?
    suspend fun postAd(adData: AdData): Boolean
    suspend fun getAdList(petType: PetType): List<AdViewData>?
    suspend fun getAd(id: Int): AdViewData?
}
