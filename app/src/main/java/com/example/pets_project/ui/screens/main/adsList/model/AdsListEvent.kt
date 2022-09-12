package com.example.pets_project.ui.screens.main.adsList.model

import android.content.Context
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.model.PetType

sealed class AdsListEvent {

    data class GetAdList(val value: Context) : AdsListEvent()
    data class ChangeFilterOption(val value: PetType) : AdsListEvent()
    data class OpenAd(val value: AdViewData) : AdsListEvent()
}
