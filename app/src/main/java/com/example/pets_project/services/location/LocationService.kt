package com.example.pets_project.services.location

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng

interface LocationService {
    fun getDefaultLocation(): Location
    fun getPosition(location: Location): LatLng
    fun requestLocationResultCallback(
        locationResultCallback: (LocationResult) -> Unit
    )
}
