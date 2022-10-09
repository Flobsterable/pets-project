package com.example.pets_project.ui.screens.main.addAd.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.pets_project.utils.Callback

@Composable
fun LocationPermissionsAndSettingDialogs(
    updateCurrentLocation: Callback,
) {
    var requestLocationSetting by remember { mutableStateOf(false) }

    if (ContextCompat.checkSelfPermission(
            LocalContext.current, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        SideEffect {
            requestLocationSetting = true
        }
    } else {
        LocationPermissionsDialog(
            onPermissionGranted = {
                requestLocationSetting = true
            },
            onPermissionDenied = {
                requestLocationSetting = true
            },
        )
    }

    if (requestLocationSetting) {

        LocationSettingDialog(
            onSuccess = {
                requestLocationSetting = false
                updateCurrentLocation()
            },
            onFailure = {
                requestLocationSetting = false
                updateCurrentLocation()
            },
        )
    }
}

@Composable
fun LocationPermissionsDialog(
    onPermissionGranted: Callback,
    onPermissionDenied: Callback,
) {
    val requestLocationPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->

        if (isGranted) {
            onPermissionGranted()
        } else {
            onPermissionDenied()
        }
    }

    SideEffect {
        requestLocationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
    }
}
