package com.example.pets_project.ui.screens.ad.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import com.example.pets_project.R

@Composable
fun ImageView(imageUri: String) {
    AsyncImage(
        model = imageUri,
        contentDescription = stringResource(id = R.string.cd_pet_photo),
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.fillMaxWidth()
    )
}
