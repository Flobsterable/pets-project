package com.example.pets_project.ui.screens.main.addAd.model

enum class PetType {
    Cat, Dog, Other
}

data class AdData(
    val nameAd: String = "",
    val descriptionAd: String = "",
    val petType: PetType = PetType.Cat,
    val location: String = ""
)
