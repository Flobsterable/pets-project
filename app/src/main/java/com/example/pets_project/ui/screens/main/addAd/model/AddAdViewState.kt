package com.example.pets_project.ui.screens.main.addAd.model

import android.graphics.Bitmap

enum class AddAdSubState {
    AddPhoto, PhotoPreview, AddAddress, AdDescription
}

data class AddAdViewState(
    val addAdSubState: AddAdSubState = AddAdSubState.AddPhoto,
    val adName: String = "",
    val adDescription: String = "",
    val petType: PetType = PetType.Cat,
    val location: String = "",
    val photo: Bitmap? = null

)
