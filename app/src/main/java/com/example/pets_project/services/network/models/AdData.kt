package com.example.pets_project.services.network.models

import com.google.android.gms.maps.model.LatLng

data class AdData(
    val id: Int,
    val petType: String,
    val imageUrl: String,
    val title: String,
    val description: String,
    val geoPosition: GeoPosition
)

data class GeoPosition(
    val lat: Double,
    val lng: Double
)
