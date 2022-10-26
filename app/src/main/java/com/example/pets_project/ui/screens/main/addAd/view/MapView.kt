package com.example.pets_project.ui.screens.main.addAd.view

import android.graphics.Bitmap
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.utils.Callback
import com.example.pets_project.utils.VectorToBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapView(
    currentLocation: LatLng,
    cameraPositionState: CameraPositionState
) {
    val context = LocalContext.current
    val bitmapMarker: Bitmap = VectorToBitmap.ToBitmap
        .getBitmapFromVectorDrawable(context, R.drawable.ic_location_point)

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(zoomControlsEnabled = false)
        )
    }
    Column() {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = mapUiSettings,
        ) {
            Marker(
                icon = BitmapDescriptorFactory.fromBitmap(bitmapMarker),
                state = MarkerState(position = currentLocation)
            )
        }
    }
}

@Composable
fun GpsButton(onClick: Callback) {
    Button(
        onClick = { onClick },
        contentPadding = PaddingValues(11.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .size(56.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_user_location),
            contentDescription = stringResource(id = R.string.cd_button_geoposition)
        )
    }
}
