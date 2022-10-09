package com.example.pets_project.ui.screens.ad

import android.graphics.Bitmap
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.pets_project.R
import com.example.pets_project.utils.VectorToBitmap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(geoPosition: LatLng) {

    val context = LocalContext.current
    val bitmapMarker: Bitmap = VectorToBitmap.ToBitmap
        .getBitmapFromVectorDrawable(context, R.drawable.ic_location_point)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(geoPosition, 16f)
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = geoPosition),
            icon = BitmapDescriptorFactory.fromBitmap(bitmapMarker)
        )
    }
}
