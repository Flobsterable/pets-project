package com.example.pets_project.ui.screens.main.adsList.model

import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.model.PetType

sealed class AdsListEvent {

    object GetAdList : AdsListEvent()
    data class ChangeFilterOption(val value: PetType) : AdsListEvent()
    data class OpenAd(val value: AdViewData) : AdsListEvent()
}
