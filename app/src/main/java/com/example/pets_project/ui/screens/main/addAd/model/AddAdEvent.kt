package com.example.pets_project.ui.screens.main.addAd.model

import android.net.Uri
import com.example.pets_project.ui.screens.main.model.PetType

sealed class AddAdEvent {
    object AddAddress : AddAdEvent()
    object PlaceAd : AddAdEvent()
    object GetCurrentLocation : AddAdEvent()
    object ConfirmAddress : AddAdEvent()
    object NavigateToMainScreen : AddAdEvent()

    data class NameAdChanged(val value: String) : AddAdEvent()
    data class DescriptionAdChanged(val value: String) : AddAdEvent()
    data class TypePetChanged(val value: PetType) : AddAdEvent()
    data class PhotoChanged(val value: Uri) : AddAdEvent()
    data class ChangedState(val value: AddAdSubState?) : AddAdEvent()
}
