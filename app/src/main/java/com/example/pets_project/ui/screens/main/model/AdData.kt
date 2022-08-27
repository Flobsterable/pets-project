package com.example.pets_project.ui.screens.main.addAd.model

import android.net.Uri
import com.example.pets_project.ui.screens.main.model.PetType

data class GeoPosition(
    val lat: Double,
    val lng: Double
)

data class AdData(
    val nameAd: String = "",
    val descriptionAd: String = "",
    val petType: PetType = PetType.Cat,
    val photoUri: Uri? = null,
    val location: GeoPosition = GeoPosition(0.0, 0.0)
)
