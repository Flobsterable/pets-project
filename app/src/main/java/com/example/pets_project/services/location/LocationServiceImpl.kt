package com.example.pets_project.services.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import javax.inject.Inject

class LocationServiceImpl @Inject constructor(
    private val appContext: Context
) : LocationService {

    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(appContext)

    override fun getDefaultLocation(): Location {
        val location = Location(LocationManager.GPS_PROVIDER)
        val moscow = LatLng(55.755819, 37.617644)
        location.latitude = moscow.latitude
        location.longitude = moscow.longitude
        return location
    }

    override fun getPosition(location: Location): LatLng {
        return LatLng(
            location.latitude,
            location.longitude
        )
    }
    @SuppressLint("MissingPermission")
    override fun requestLocationResultCallback(
        locationResultCallback: (LocationResult) -> Unit
    ) {

        val locationCallback = object : LocationCallback() {

            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                locationResultCallback(locationResult)
                fusedLocationProviderClient.removeLocationUpdates(this)
            }
        }

        val locationRequest = LocationRequest.create().apply {
            interval = 0
            fastestInterval = 0
            priority = Priority.PRIORITY_HIGH_ACCURACY
        }
        Looper.myLooper()?.let { looper ->
            fusedLocationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                looper
            )
        }
    }
}
