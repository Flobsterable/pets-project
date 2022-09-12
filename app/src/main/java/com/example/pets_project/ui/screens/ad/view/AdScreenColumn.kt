package com.example.pets_project.ui.screens.ad.view

import android.location.Geocoder
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.pets_project.R
import com.example.pets_project.ui.screens.ad.model.AdSubState
import com.example.pets_project.ui.screens.ad.model.AdViewState
import com.example.pets_project.ui.theme.gray

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdScreenColumn(viewState: State<AdViewState?>, onClick: (AdSubState) -> Unit) {
    Column() {

        when (
            (viewState.value?.adData?.photoUri ?: "") != ""
        ) {
            true -> {
                Card(
                    onClick = { onClick(AdSubState.Image) },
                    modifier = Modifier
                        .aspectRatio(1f)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        model = viewState.value?.adData!!.photoUri,
                        contentDescription = stringResource(id = R.string.cd_pet_photo),
                    )
                }
            }
            false -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .background(gray),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_empty_image),
                        contentDescription = stringResource(
                            id = R.string.cd_empty_image
                        )
                    )
                }
            }
        }
        Row(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_location_point),
                contentDescription = stringResource(id = R.string.cd_location_point),
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = viewState.value!!.adData?.address!!,
                style = MaterialTheme.typography.body1,
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .weight(1f)
            )
            Button(
                onClick = { onClick(AdSubState.Map) },
                modifier = Modifier.size(136.dp, 48.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = stringResource(id = R.string.button_on_map))
            }
        }
        Text(
            text = viewState.value?.adData?.descriptionAd ?: "",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp)
        )
    }
}
