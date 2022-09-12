package com.example.pets_project.ui.screens.ad.model

import com.example.pets_project.ui.screens.main.addAd.model.AdViewData

sealed class AdEvent {
    data class ChangeState(val value: AdSubState) : AdEvent()
    data class SaveData(val value: AdViewData) : AdEvent()
}
