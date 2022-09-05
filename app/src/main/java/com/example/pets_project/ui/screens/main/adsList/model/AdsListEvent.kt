package com.example.pets_project.ui.screens.main.adsList.model

import com.example.pets_project.ui.screens.main.model.PetType

sealed class AdsListEvent {

    data class ChangeFilterOption(val value: PetType) : AdsListEvent()
    data class OpenAd(val value: Int) : AdsListEvent()
}
