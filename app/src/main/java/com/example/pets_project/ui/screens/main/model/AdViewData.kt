package com.example.pets_project.ui.screens.main.addAd.model

import android.os.Parcelable
import com.example.pets_project.ui.screens.main.model.PetType
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AdViewData(
    val id: Int = 0,
    val nameAd: String? = "",
    val descriptionAd: String? = "",
    val petType: PetType = PetType.Cat,
    val photoUri: String? = null,
    val location: LatLng = LatLng(0.0, 0.0),
    val address: String? = ""
) : Parcelable
