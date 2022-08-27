package com.example.pets_project.ui.screens.main.addAd.model

import android.net.Uri
import com.example.pets_project.ui.screens.main.model.PetType
import com.example.pets_project.utils.EditTextErrorState

enum class AddAdSubState {
    AddPhoto, PhotoPreview, AddAddress, AdDescription
}

data class AddAdViewState(
    val addAdSubState: AddAdSubState = AddAdSubState.AddPhoto,
    val adName: String = "",
    val adDescription: String = "",
    val petType: PetType = PetType.Cat,
    val location: GeoPosition = GeoPosition(0.0, 0.0),
    val photo: Uri? = null,

    val adNameTextErrorState: EditTextErrorState = EditTextErrorState.None,
    val adDescriptionTextErrorState: EditTextErrorState = EditTextErrorState.None

)
