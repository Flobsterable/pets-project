package com.example.pets_project.services.network

import android.util.Log
import com.example.pets_project.services.modelParser.ModelParser
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.services.network.models.UserLoginData
import com.example.pets_project.services.network.models.UserRegistrationData
import com.example.pets_project.services.network.models.UserTokenResponse
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.viewModels.AdViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class NetworkServiceImpl @Inject constructor(
    private var modelParser: ModelParser
) : NetworkService {

    private fun getBaseUr(): String = "https://petsproject.issart.com/api/1.0.0/"
    private val gson = GsonConverterFactory.create(GsonBuilder().create())

    private var adList: List<AdData>? = null

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
        when (healthCheckResponse.code()) {
            200 -> Log.e("network", "OK")
            else -> Log.e("network. error", "${healthCheckResponse.code()}")
        }

        val tokenResponse = networkService.login(userLoginData)

        return when (tokenResponse.code()) {
            200 -> {
                Log.e("network", "Login: OK")
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
        when (healthCheckResponse.code()) {
            200 -> Log.e("network", "OK")
            else -> Log.e("network. error", "${healthCheckResponse.code()}")
        }

        val tokenResponse = networkService.registration(userRegistrationData)
        return when (tokenResponse.code()) {
            200 -> {
                Log.e("network", "Registration: OK")
                tokenResponse.body()
            }
            else -> {
                Log.e("ErrorCode Registration:", "${tokenResponse.code()}")
                null
            }
        }
    }

    override suspend fun postAd(adData: AdData): Boolean {

        val postAdResponse = networkService.postAd(adData)
        Log.e("post ad",
            "\n${adData.title}\n${adData.description}\n${adData.petType}\n${adData.imageUrl}\n${adData.geoPosition}")
        return when (postAdResponse.code()) {
            200 -> true
            else -> false
        }
    }

    private suspend fun getAdListFromServer(petType: String): List<AdData>? {
        val adsListResponse = networkService.getAds(petType)

        return when (adsListResponse.code()) {
            200 -> adsListResponse.body()
            else -> {
                Log.e("get ads", "${adsListResponse.code()}")
                null
            }
        }
    }

    override suspend fun getAdList(petType: PetType): List<AdViewData>? {

        val type = when (petType) {
            PetType.Dog -> "dog"
            PetType.Cat -> "cat"
            PetType.Other -> "other"
            else -> ""
        }

        adList = getAdListFromServer(type)

        if (adList != null) {
            return modelParser.adListParser(adList!!)
        }
        return null
    }

    override suspend fun getAd(id: Int): AdViewData? {

        if (adList == null) {
            adList = getAdListFromServer("")
            if (adList == null) {
                return null
            }
        }
        for (item in adList!!) {
            if (item.id == id) {
                return modelParser.adParser(item)
            }
        }
        return null
    }
}
