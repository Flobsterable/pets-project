package com.example.pets_project.ui.screens.main.adsList.model

import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.ui.screens.main.model.PetType

data class AdsListViewState(
    val petsTypeFilter: PetType = PetType.All,
    val adsList: List<AdData>? = null
)
