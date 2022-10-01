package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.utils.LocationUtil
import com.example.pets_project.viewModels.AddAdViewModel
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AddAddressColumn(addAdViewModel: AddAdViewModel) {

    val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(LocalContext.current)

    var currentLocation by remember { mutableStateOf(LocationUtil.getDefaultLocation()) }

    var requestLocationUpdate by remember { mutableStateOf(true) }

    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        LocationUtil.getPosition(currentLocation), 16f
    )
    BoxWithConstraints(
        modifier = Modifier.padding(bottom = 56.dp)
    ) {

        MyGoogleMap(
            currentLocation = currentLocation,
            cameraPositionState = cameraPositionState,
            onGpsIconClick = {
                requestLocationUpdate = true
            }
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MarkButton(
                onClick = { addAdViewModel.obtainEvent(AddAdEvent.ConfirmAddress) },
                modifier = Modifier,
                stringResId = R.string.button_done,
                painterResId = R.drawable.ic_done,
                contentDescriptionResId = R.string.cd_done
            )
        }
    }

    if (requestLocationUpdate) {
        LocationPermissionsAndSettingDialogs(
            updateCurrentLocation = {
                requestLocationUpdate = false
                LocationUtil.requestLocationResultCallback(fusedLocationProviderClient) { locationResult ->

                    locationResult.locations[0]?.let { location ->
                        currentLocation = location
                    }
                }
            }
        )
    }
}
