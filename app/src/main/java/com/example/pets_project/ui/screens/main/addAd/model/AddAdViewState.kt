package com.example.pets_project.ui.screens.main.addAd.model

import android.graphics.Bitmap
import android.net.Uri
import com.example.pets_project.ui.screens.main.model.PetType

enum class AddAdSubState {
    AddPhoto, PhotoPreview, AddAddress, AdDescription
}

data class AddAdViewState(
    val addAdSubState: AddAdSubState = AddAdSubState.AddPhoto,
    val adName: String = "",
    val adDescription: String = "",
    val petType: PetType = PetType.Cat,
    val location: String = "",
    val photo: Uri? = null

)
