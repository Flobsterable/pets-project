package com.example.pets_project.ui.screens.main.addAd.view

import android.graphics.Bitmap
import android.location.Location
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pets_project.R
import com.example.pets_project.utils.LocationUtil
import com.example.pets_project.utils.VectorToBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.maps.android.compose.*

@Composable
fun MyGoogleMap(
    currentLocation: Location,
    cameraPositionState: CameraPositionState,
    onGpsIconClick: () -> Unit
) {
    val context = LocalContext.current
    val bitmapMarker: Bitmap = VectorToBitmap.ToBitmap
        .getBitmapFromVectorDrawable(context, R.drawable.ic_location_point)

    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(zoomControlsEnabled = false)
        )
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
    ) {
        Marker(
            icon = BitmapDescriptorFactory.fromBitmap(bitmapMarker),
            state = MarkerState(position = LocationUtil.getPosition(currentLocation))
        )
    }

    GpsIconButton(onIconClick = onGpsIconClick)
}

@Composable
fun GpsIconButton(onIconClick: () -> Unit) {
    Button(
        onClick = { onIconClick },
        contentPadding = PaddingValues(11.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_user_location),
            contentDescription = ""
        )
    }
}
