package com.example.pets_project.ui.screens.ad.model

import com.example.pets_project.ui.screens.main.addAd.model.AdViewData

enum class AdState {
    Loading, Complete, Empty
}

enum class AdSubState {
    Ad, Map, Image
}

data class AdViewState(
    val adState: AdState = AdState.Loading,
    val adData: AdViewData? = null,
    val adSubState: AdSubState = AdSubState.Ad
)
