package com.example.pets_project.ui.screens.main.addAd.view

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.ui.screens.login.view.MarkButton
import com.example.pets_project.ui.screens.main.addAd.model.AddAdEvent
import com.example.pets_project.viewModels.AddAdViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun AddAddressColumn(addAdViewModel: AddAdViewModel) {

    LaunchedEffect(key1 = Unit, block = {
        addAdViewModel.obtainEvent(AddAdEvent.SetDefaultLocation)
    })

    var requestLocationUpdate by remember { mutableStateOf(true) }
    val cameraPositionState = rememberCameraPositionState()
    cameraPositionState.position = CameraPosition.fromLatLngZoom(
        addAdViewModel.viewState.value!!.userLocation, 16f
    )
    BoxWithConstraints(
        modifier = Modifier.padding(bottom = 56.dp)
    ) {

        MapView(
            currentLocation = addAdViewModel.viewState.value!!.userLocation,
            cameraPositionState = cameraPositionState,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row() {
                Spacer(modifier = Modifier.weight(1f))
                GpsButton(onClick = {requestLocationUpdate = true})
            }
            Spacer(modifier = Modifier.weight(1f))

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
                addAdViewModel.obtainEvent(AddAdEvent.UpdateLocation)
            }
        )
    }
}
