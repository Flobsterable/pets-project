package com.example.pets_project.services.network.models

data class AdData(
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
