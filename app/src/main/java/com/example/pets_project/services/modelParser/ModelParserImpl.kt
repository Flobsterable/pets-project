package com.example.pets_project.services.modelParser

import android.content.Context
import android.location.Geocoder
import android.net.Uri
import android.util.Log
import com.example.pets_project.services.network.models.AdData
import com.example.pets_project.ui.screens.main.addAd.model.AdViewData
import com.example.pets_project.ui.screens.main.model.PetType
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class ModelParserImpl @Inject constructor(
    private val appContext: Context
) : ModelParser {

    override fun adListParser(adList: List<AdData>): List<AdViewData> {
        val adViewList = mutableListOf<AdViewData>()
        for (item in adList) {
            Log.e("","${item.geoPosition}")
            adViewList.add(adParser(item))
        }
        return adViewList
    }

    override fun adParser(adData: AdData): AdViewData {
        val geocoder = Geocoder(appContext)
        val fullAddress = geocoder.getFromLocation(
            adData.geoPosition.lat,
            adData.geoPosition.lng,
            1
        )

        val address = when (fullAddress.isNotEmpty()) {
            true -> fullAddress[0].getAddressLine(0)
            false -> ""
        }

        return AdViewData(
            id = adData.id,
            nameAd = adData.title,
            descriptionAd = adData.description,
            petType = when (adData.petType) {
                "cat" -> PetType.Cat
                "dog" -> PetType.Dog
                else -> PetType.Other
            },
            photoUri = adData.imageUrl,
            location = LatLng(adData.geoPosition.lat, adData.geoPosition.lng),
            address = address
        )
    }
}
