package com.example.pets_project.ui.screens.main.addAd.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.example.pets_project.ui.screens.main.model.PetType
import kotlinx.android.parcel.Parcelize

data class GeoPosition(
    val lat: Double,
    val lng: Double
)
@Parcelize
data class AdViewData(
    val id: Int = 0,
    val nameAd: String? = "",
    val descriptionAd: String? = "",
    val petType: PetType = PetType.Cat,
    val photoUri: Parcelable? = null,
    val location: Array<Double> = arrayOf(0.0, 0.0),
    val address: String? = ""
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AdViewData

        if (id != other.id) return false
        if (nameAd != other.nameAd) return false
        if (descriptionAd != other.descriptionAd) return false
        if (petType != other.petType) return false
        if (photoUri != other.photoUri) return false
        if (!location.contentEquals(other.location)) return false
        if (address != other.address) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + (nameAd?.hashCode() ?: 0)
        result = 31 * result + (descriptionAd?.hashCode() ?: 0)
        result = 31 * result + petType.hashCode()
        result = 31 * result + (photoUri?.hashCode() ?: 0)
        result = 31 * result + location.contentHashCode()
        result = 31 * result + (address?.hashCode() ?: 0)
        return result
    }
}
