package com.example.pets_project.ui.screens.main.addAd.model

import com.example.pets_project.ui.screens.main.model.PetType

data class AdData(
    val nameAd: String = "",
    val descriptionAd: String = "",
    val petType: PetType = PetType.Cat,
    val location: String = ""
)
